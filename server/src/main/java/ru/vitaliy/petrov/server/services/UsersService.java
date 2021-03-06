package ru.vitaliy.petrov.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vitaliy.petrov.server.error.ApiRequestException;
import ru.vitaliy.petrov.server.error.ForbiddenApiException;
import ru.vitaliy.petrov.server.error.InternalApiException;
import ru.vitaliy.petrov.server.error.NotFoundApiException;
import ru.vitaliy.petrov.server.forms.requests.UserLoginRequest;
import ru.vitaliy.petrov.server.forms.requests.UserRegistrationRequest;
import ru.vitaliy.petrov.server.forms.requests.userprofile.UserUpdateRequest;
import ru.vitaliy.petrov.server.models.*;
import ru.vitaliy.petrov.server.repositories.*;
import ru.vitaliy.petrov.server.security.JwtUtil;
import ru.vitaliy.petrov.server.security.UsersDetails;
import ru.vitaliy.petrov.server.security.UsersDetailsService;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
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

    @Autowired
    private CarAccidentEntityRepository carAccidentEntityRepository;

    @Autowired
    private CarAccidentParticipantRepository carAccidentParticipantRepository;

    @Autowired
    private ChangelogService changelogService;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private CarAccidentEntityStateRepository carAccidentEntityStateRepository;

    @Override
    public String register(UserRegistrationRequest userRegistrationRequest) {
        final String phoneNumber = userRegistrationRequest.getPhoneNumber();
        final String password = userRegistrationRequest.getPassword();
        final String roleName = userRegistrationRequest.getRoleName();

        Optional<UserRole> role = userRoleRepository.findByUserRoleName(roleName);

        if (role.isEmpty()) {
            throw new InternalApiException("???? ?????????????? ?????????? ???????? ?????? ?????????????? ????????????????????????");
        }

        final Optional<Users> user = usersRepository.findByUserPhoneNumber(phoneNumber);
        if (user.isPresent()) {
            throw new NotFoundApiException("???????????????????????? " + phoneNumber + " ?????? ??????????????????????????????");
        }

        Optional<UserState> state;
        if (role.get().getUserRoleName().equals("USER")) {
            state = userStateRepository.findByUserStateName("????????????????????????");
        } else {
            state = userStateRepository.findByUserStateName("???? ????????????");
        }

        if (state.isEmpty()) {
            throw new InternalApiException("???? ?????????????? ?????????? ?????????????????? ?????? ?????????????? ????????????????????????");
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
            throw new NotFoundApiException("???????????????????????? " + phoneNumber + " ???? ??????????????????????????????");
        }

        if (!passwordEncoder.matches(password, user.get().getUserPassword())) {
            throw new NotFoundApiException("???????????????????????? ????????????");
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
            throw new ForbiddenApiException("???????????????????????? ?????????????? ????????????");
        }

        final String username = jwtUtil.extractUserName(jwtToken);
        final UsersDetails usersDetails = usersDetailsService.loadUserByUsername(username);

        jwtUtil.validateToken(jwtToken, usersDetails);

        return jwtUtil.generateToken(usersDetails);
    }

    @Override
    public String updateUser(UserUpdateRequest userUpdateRequest) {

        String userPhoneNumber = userUpdateRequest.getPhoneNumber();
        String updatedPassword = userUpdateRequest.getPassword();

        Optional<Users> userCandidate = usersRepository.findByUserPhoneNumber(userPhoneNumber);
        if (userCandidate.isEmpty()) {
            throw new NotFoundApiException("???????????????????????? ???? ????????????");
        }
        Users user = userCandidate.get();
        String hashPassword = passwordEncoder.encode(updatedPassword);
        user.setUserPassword(hashPassword);

        usersRepository.save(user);

        final UsersDetails usersDetails = usersDetailsService.loadUserByUsername(user.getUserPhoneNumber());

        return jwtUtil.generateToken(usersDetails);
    }

    @Override
    public String deleteUser(Long userID) {
        Optional<Users> userCandidate = usersRepository.findById(userID);
        if (userCandidate.isEmpty()) {
            throw new NotFoundApiException("???????????????????????? ???? ????????????");
        }
        driverLicenseService.deleteDriverLicense(userCandidate.get().getId());
        usersRepository.delete(userCandidate.get());
        return "???????????????????????? ?????? ????????????";
    }

    public String changeUserState(Long userID, String userState) {
        Optional<Users> userCandidate = usersRepository.findById(userID);
        if (userCandidate.isEmpty()) {
            throw new NotFoundApiException("???????????????????????? ???? ????????????");
        }
        Users user = userCandidate.get();
        if (user.getUserRole().getUserRoleName().equals("USER")) {
            throw new ApiRequestException("???? ???? ???????????? ?????????????? ?? ?????????? ??????????????");
        } else {
            Optional<UserState> userStateCandidate = userStateRepository.findByUserStateName(userState);
            if (userStateCandidate.isEmpty()) {
                throw new ApiRequestException("???????????? ?????????????? ???? ????????????????????");
            } else {
                user.setUserState(userStateCandidate.get());
                usersRepository.save(user);

                Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findFirstByTrafficPoliceOfficer(null);

                Optional<CarAccidentEntityState> carAccidentEntityState = carAccidentEntityStateRepository.findByCarAccidentEntityState("???????????????????????? ??????????????????");

                if (carAccidentEntityState.isEmpty()) {
                    throw new InternalApiException("?????????????????????? ???????? ???????????? ????????????????");
                }

                if (carAccidentEntityCandidate.isPresent()) {
                    carAccidentEntityCandidate.get().setTrafficPoliceOfficer(user);
                    carAccidentEntityCandidate.get().setEntityState(carAccidentEntityState.get());
                    carAccidentEntityRepository.save(carAccidentEntityCandidate.get());
                    changelogService.addNewRecord(carAccidentEntityCandidate.get().getId(), "?????????????????? ???????? ?? ?????? ???" + carAccidentEntityCandidate.get().getId() + " ???????? ????????????????: ?????????????? ?????????????????? - ???????????????????????? ??????????????????", "??????????", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));
                    Optional<List<CarAccidentParticipant>> carAccidentParticipantList = carAccidentParticipantRepository.findAllByCarAccidentEntity(carAccidentEntityCandidate.get());

                    if (carAccidentParticipantList.isEmpty()) {
                        throw new ApiRequestException("???? ?????????????? ?????????? ???????????????????? ??????");
                    }

                    for (CarAccidentParticipant participant : carAccidentParticipantList.get()) {
                        Chat chat = Chat
                                .builder()
                                .carAccidentEntity(carAccidentEntityCandidate.get())
                                .carAccidentParticipant(participant.getCarAccidentParticipant())
                                .trafficPoliceOfficer(carAccidentEntityCandidate.get().getTrafficPoliceOfficer())
                                .build();

                        chatRepository.save(chat);

                        changelogService.addNewRecord(carAccidentEntityCandidate.get().getId(), "?????? ?? ???????????????????? " + participant.getCarAccidentParticipant().getId() + " ???? ?????? ???" + carAccidentEntityCandidate.get().getId() + " ?????? ????????????????", "??????????????????", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));
                    }
                }
            }
        }
        return "???????????? ?????? ??????????????";
    }

    public String getUserRole(Long userID) {
        Optional<Users> userCandidate = usersRepository.findById(userID);
        if (userCandidate.isEmpty()) {
            throw new NotFoundApiException("???????????????????????? ???? ????????????");
        }
        return userCandidate.get().getUserRole().getUserRoleName();
    }

}
