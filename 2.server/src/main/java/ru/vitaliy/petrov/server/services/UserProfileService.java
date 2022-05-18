package ru.vitaliy.petrov.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vitaliy.petrov.server.error.ApiRequestException;
import ru.vitaliy.petrov.server.error.InternalApiException;
import ru.vitaliy.petrov.server.forms.requests.UserProfileCreationRequest;
import ru.vitaliy.petrov.server.forms.requests.UserProfileUpdateRequest;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.models.DriverLicense;
import ru.vitaliy.petrov.server.models.UserProfile;
import ru.vitaliy.petrov.server.models.Users;
import ru.vitaliy.petrov.server.repositories.DriverLicenseRepository;
import ru.vitaliy.petrov.server.repositories.UserProfileRepository;
import ru.vitaliy.petrov.server.repositories.UsersRepository;

import java.util.Optional;

@Service
public class UserProfileService implements IUserProfileService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private DriverLicenseRepository driverLicenseRepository;

    @Override
    public CreationResponse createNewUserProfile(UserProfileCreationRequest userProfileCreationRequest, Long userID) {
        Optional<Users> userCandidate = usersRepository.findById(userID);

        if (userCandidate.isEmpty()) {
            throw new ApiRequestException("Пользователь, создавший запрос, не был найден");
        }

        final Users user = userCandidate.get();
        final String fullName = userProfileCreationRequest.getFullName();
        final String dateOfBirth = userProfileCreationRequest.getDateOfBirth();
        final String residentialAddress = userProfileCreationRequest.getResidentialAddress();
        final String placeOfWork = userProfileCreationRequest.getPlaceOfWork();
        final String positionAtWork = userProfileCreationRequest.getPositionAtWork();
        final String workPhoneNumber = userProfileCreationRequest.getWorkPhoneNumber();
        final String driverLicenseNumber = userProfileCreationRequest.getDriverLicenseNumber();

        if (fullName == null || dateOfBirth == null || residentialAddress == null || placeOfWork == null || positionAtWork == null || workPhoneNumber == null || driverLicenseNumber == null) {
            throw new ApiRequestException("Неправильный формат введенных данных");
        }

        Optional<DriverLicense> driverLicenseCandidate = driverLicenseRepository.findByDriverLicenseNumber(driverLicenseNumber);

        if (driverLicenseCandidate.isEmpty()) {
            throw new ApiRequestException("Введенный номер водительских прав не был найден");
        }

        final DriverLicense driverLicense = driverLicenseCandidate.get();

        UserProfile userProfile = UserProfile
                .builder()
                .user(user)
                .userFullName(fullName)
                .userDateOfBirth(dateOfBirth)
                .userResidentialAddress(residentialAddress)
                .userPlaceOfWork(placeOfWork)
                .userPositionAtWork(positionAtWork)
                .userWorkPhoneNumber(workPhoneNumber)
                .userDriverLicense(driverLicense)
                .build();

        userProfileRepository.save(userProfile);

        Optional<UserProfile> createdUserProfile = userProfileRepository.findByUser(user);

        if (createdUserProfile.isEmpty()) {
            throw new InternalApiException("Не удалось добавить пользователя");
        }

        return new CreationResponse("UserProfile", createdUserProfile.get().getId());
    }

    @Override
    public UserProfile getUserProfile(Long userID) {
        return findUserProfileByUserID(userID);
    }

    @Override
    public String updateUserProfile(UserProfileUpdateRequest userProfileUpdateRequest, Long userID) {

        final String updatedFullName = userProfileUpdateRequest.getUpdatedFullName();
        final String updatedDateOfBirth = userProfileUpdateRequest.getUpdatedDateOfBirth();
        final String updatedResidentialAddress = userProfileUpdateRequest.getUpdatedResidentialAddress();
        final String updatedPlaceOfWork = userProfileUpdateRequest.getUpdatedPlaceOfWork();
        final String updatedPositionAtWork = userProfileUpdateRequest.getUpdatedPositionAtWork();
        final String updatedWorkPhoneNumber = userProfileUpdateRequest.getUpdatedWorkPhoneNumber();
        final String updatedDriverLicenseNumber = userProfileUpdateRequest.getUpdatedDriverLicenseNumber();

        if (updatedFullName == null && updatedDateOfBirth == null && updatedResidentialAddress == null && updatedPlaceOfWork == null && updatedPositionAtWork == null && updatedWorkPhoneNumber == null && updatedDriverLicenseNumber == null) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        DriverLicense updatedDriverLicense = null;
        Optional<DriverLicense> driverLicenseCandidate = driverLicenseRepository.findByDriverLicenseNumber(updatedDriverLicenseNumber);

        if (driverLicenseCandidate.isPresent()) {
            updatedDriverLicense = driverLicenseCandidate.get();
        }

        UserProfile userProfile = findUserProfileByUserID(userID);

        if (updatedFullName != null) {
            userProfile.setUserFullName(updatedFullName);
        }

        if (updatedDateOfBirth != null) {
            userProfile.setUserDateOfBirth(updatedDateOfBirth);
        }

        if (updatedResidentialAddress != null) {
            userProfile.setUserResidentialAddress(updatedResidentialAddress);
        }

        if (updatedPlaceOfWork != null) {
            userProfile.setUserPlaceOfWork(updatedPlaceOfWork);
        }

        if (updatedPositionAtWork != null) {
            userProfile.setUserPositionAtWork(updatedPositionAtWork);
        }

        if (updatedWorkPhoneNumber != null) {
            userProfile.setUserWorkPhoneNumber(updatedWorkPhoneNumber);
        }

        if (updatedDriverLicense != null) {
            userProfile.setUserDriverLicense(updatedDriverLicense);
        }

        userProfileRepository.save(userProfile);
        return "Пользователь был изменен";
    }

    @Override
    public String deleteUserProfile(Long userID) {
        UserProfile userProfile = findUserProfileByUserID(userID);
        userProfileRepository.delete(userProfile);
        return "Пользователь был удален";
    }

    private UserProfile findUserProfileByUserID(Long userID) {
        Optional<Users> userCandidate = usersRepository.findById(userID);

        if (userCandidate.isEmpty()) {
            throw new ApiRequestException("Пользователь, создавший запрос, не был найден");
        }

        final Users user = userCandidate.get();
        Optional<UserProfile> userProfileCandidate = userProfileRepository.findByUser(user);

        if (userProfileCandidate.isEmpty()) {
            throw new ApiRequestException("У заданного пользователя нет профиля");
        }
        return userProfileCandidate.get();
    }

}
