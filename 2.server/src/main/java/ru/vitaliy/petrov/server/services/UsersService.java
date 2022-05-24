package ru.vitaliy.petrov.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vitaliy.petrov.server.error.ApiRequestException;
import ru.vitaliy.petrov.server.error.ForbiddenApiException;
import ru.vitaliy.petrov.server.error.NotFoundApiException;
import ru.vitaliy.petrov.server.forms.requests.UserLoginRequest;
import ru.vitaliy.petrov.server.forms.requests.UserRegistrationRequest;
import ru.vitaliy.petrov.server.forms.requests.userprofile.UserUpdateRequest;
import ru.vitaliy.petrov.server.models.UserRole;
import ru.vitaliy.petrov.server.models.UserState;
import ru.vitaliy.petrov.server.models.Users;
import ru.vitaliy.petrov.server.repositories.UserRoleRepository;
import ru.vitaliy.petrov.server.repositories.UserStateRepository;
import ru.vitaliy.petrov.server.repositories.UsersRepository;
import ru.vitaliy.petrov.server.security.JwtUtil;
import ru.vitaliy.petrov.server.security.UsersDetails;
import ru.vitaliy.petrov.server.security.UsersDetailsService;

import java.util.Optional;

@Service
public class UsersService implements IUsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserStateRepository userStateRepository;

    @Autowired
    private DriverLicenseService driverLicenseService;

    @Autowired
    private UsersDetailsService usersDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String register(UserRegistrationRequest userRegistrationRequest) {
        final String phoneNumber = userRegistrationRequest.getPhoneNumber();
        final String password = userRegistrationRequest.getPassword();
        final String roleName = userRegistrationRequest.getRoleName();

        Optional<UserRole> role = userRoleRepository.findByUserRoleName(roleName);

        final Optional<Users> user = usersRepository.findByUserPhoneNumber(phoneNumber);
        if (user.isPresent()) {
            throw new NotFoundApiException("Пользователь " + phoneNumber + " уже зарегистрирован");
        }

        Optional<UserState> state;
        if(role.get().getUserRoleName().equals("USER")) {
            state = userStateRepository.findByUserStateName("Пользователь");
        }
        else {
            state = userStateRepository.findByUserStateName("На отдыхе");
        }

        String hashPassword = passwordEncoder.encode(password);

        Users newUser = Users
                .builder()
                .userPhoneNumber(phoneNumber)
                .userPassword(hashPassword)
                .userRole(role.get())
                .userState(state.get())
                .build();

        usersRepository.save(newUser);
        final UsersDetails usersDetails = usersDetailsService.loadUserByUsername(phoneNumber);

        return jwtUtil.generateToken(usersDetails);
    }

    @Override
    public String login(UserLoginRequest userLoginRequest) {
        final String phoneNumber = userLoginRequest.getPhoneNumber();
        final String password = userLoginRequest.getPassword();

        final Optional<Users> user = usersRepository.findByUserPhoneNumber(phoneNumber);

        if (user.isEmpty()) {
            throw new NotFoundApiException("Пользователь " + phoneNumber + " не зарегистрирован");
        }

        if (!passwordEncoder.matches(password, user.get().getUserPassword())) {
            throw new NotFoundApiException("Неправильный пароль");
        }

        final UsersDetails usersDetails = usersDetailsService.loadUserByUsername(phoneNumber);

        return jwtUtil.generateToken(usersDetails);
    }

    public String validateToken(String jwtTokenHeader) {
        String jwtToken;
        if (jwtTokenHeader.startsWith("Bearer ")) {
            jwtToken = jwtTokenHeader.substring(7);
            System.out.println(jwtToken);
        } else {
            throw new ForbiddenApiException("Некорректный префикс токена");
        }

        final String username = jwtUtil.extractUserName(jwtToken);
        final UsersDetails usersDetails = usersDetailsService.loadUserByUsername(username);

        jwtUtil.validateToken(jwtToken, usersDetails);

        return jwtUtil.generateToken(usersDetails);
    }

    @Override
    public String updateUser(UserUpdateRequest userUpdateRequest, Long userID) {

        String updatedPhoneNumber = userUpdateRequest.getPhoneNumber();
        String updatedPassword = userUpdateRequest.getPassword();

        Optional<Users> userCandidate = usersRepository.findById(userID);
        if (userCandidate.isEmpty()) {
            throw new NotFoundApiException("Пользователь не найден");
        }
        Users user = userCandidate.get();

        if (updatedPhoneNumber != null) {
            user.setUserPhoneNumber(updatedPhoneNumber);
        }

        if (updatedPassword != null) {
            String hashPassword = passwordEncoder.encode(updatedPassword);
            user.setUserPassword(hashPassword);
        }

        usersRepository.save(user);

        final UsersDetails usersDetails = usersDetailsService.loadUserByUsername(user.getUserPhoneNumber());

        return jwtUtil.generateToken(usersDetails);
    }

    @Override
    public String deleteUser(Long userID) {
        Optional<Users> userCandidate = usersRepository.findById(userID);
        if (userCandidate.isEmpty()) {
            throw new NotFoundApiException("Пользователь не найден");
        }
        driverLicenseService.deleteDriverLicense(userCandidate.get().getId());
        usersRepository.delete(userCandidate.get());
        return "Пользователь был удален";
    }

    public String changeUserState(Long userID, String userState) {
        Optional<Users> userCandidate = usersRepository.findById(userID);
        if (userCandidate.isEmpty()) {
            throw new NotFoundApiException("Пользователь не найден");
        }
        Users user = userCandidate.get();
        if(user.getUserRole().getUserRoleName().equals("USER")) {
            throw new ApiRequestException("Вы не имеете доступа к смене статуса");
        }
        else {
            Optional<UserState> userStateCandidate = userStateRepository.findByUserStateName(userState);
            if(userStateCandidate.isEmpty()) {
                throw new ApiRequestException("Такого статуса не существует");
            }
            else{
                user.setUserState(userStateCandidate.get());
                usersRepository.save(user);
            }
        }
        return "Статус был изменен";
    }

}
