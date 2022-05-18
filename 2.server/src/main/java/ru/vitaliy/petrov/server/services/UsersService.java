package ru.vitaliy.petrov.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vitaliy.petrov.server.error.ApiRequestException;
import ru.vitaliy.petrov.server.error.ForbiddenApiException;
import ru.vitaliy.petrov.server.error.NotFoundApiException;
import ru.vitaliy.petrov.server.forms.requests.UserLoginRequest;
import ru.vitaliy.petrov.server.forms.requests.UserRegistrationRequest;
import ru.vitaliy.petrov.server.forms.requests.UserUpdateRequest;
import ru.vitaliy.petrov.server.models.UserRole;
import ru.vitaliy.petrov.server.models.Users;
import ru.vitaliy.petrov.server.repositories.UserRoleRepository;
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
    private UsersDetailsService usersDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String register(UserRegistrationRequest userRegistrationRequest) {
        final String login = userRegistrationRequest.getPhoneNumber();
        final String password = userRegistrationRequest.getPassword();
        final String roleName = userRegistrationRequest.getRoleName();

        Optional<UserRole> role = userRoleRepository.findByUserRoleName(roleName);

        if (login == null || password == null || role.isEmpty()) {
            throw new NotFoundApiException("Неправильные ключи пользователя при регистрации");
        }

        final Optional<Users> user = usersRepository.findByUserPhoneNumber(login);
        if (user.isPresent()) {
            throw new NotFoundApiException("Пользователь " + login + " уже зарегистрирован");
        }

        String hashPassword = passwordEncoder.encode(password);

        Users newUser = Users
                .builder()
                .userPhoneNumber(login)
                .userPassword(hashPassword)
                .userRole(role.get())
                .build();

        usersRepository.save(newUser);
        final UsersDetails usersDetails = usersDetailsService.loadUserByUsername(login);

        return jwtUtil.generateToken(usersDetails);
    }

    @Override
    public String login(UserLoginRequest userLoginRequest) {
        final String login = userLoginRequest.getPhoneNumber();
        final String password = userLoginRequest.getPassword();

        final Optional<Users> user = usersRepository.findByUserPhoneNumber(login);

        if (user.isEmpty()) {
            throw new NotFoundApiException("Пользователь " + login + " не зарегистрирован");
        }

        if (!passwordEncoder.matches(password, user.get().getUserPassword())) {
            throw new NotFoundApiException("Неправильный пароль");
        }

        final UsersDetails usersDetails = usersDetailsService.loadUserByUsername(login);

        return jwtUtil.generateToken(usersDetails);
    }

    public String validateToken(String jwtTokenHeader) {
        String jwtToken;
        if (jwtTokenHeader.startsWith("Bearer ")) {
            jwtToken = jwtTokenHeader.substring(7);
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

        if (updatedPhoneNumber == null && updatedPassword == null) {
            throw new ApiRequestException("Параметры запроса указаны неверно");
        }

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
        usersRepository.delete(userCandidate.get());
        return "Пользователь был удален";
    }

}
