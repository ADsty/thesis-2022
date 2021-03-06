package ru.vitaliy.petrov.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vitaliy.petrov.server.error.ApiRequestException;
import ru.vitaliy.petrov.server.error.InternalApiException;
import ru.vitaliy.petrov.server.forms.requests.userprofile.DriverLicenseCreationRequest;
import ru.vitaliy.petrov.server.forms.requests.userprofile.DriverLicenseUpdateRequest;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.models.DriverLicense;
import ru.vitaliy.petrov.server.models.DriverLicenseCategory;
import ru.vitaliy.petrov.server.models.UserProfile;
import ru.vitaliy.petrov.server.models.Users;
import ru.vitaliy.petrov.server.repositories.DriverLicenseCategoryRepository;
import ru.vitaliy.petrov.server.repositories.DriverLicenseRepository;
import ru.vitaliy.petrov.server.repositories.UserProfileRepository;
import ru.vitaliy.petrov.server.repositories.UsersRepository;

import java.sql.Date;
import java.util.Optional;

@Service
public class DriverLicenseService implements IDriverLicenseService {

    @Autowired
    private DriverLicenseRepository driverLicenseRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private DriverLicenseCategoryRepository driverLicenseCategoryRepository;

    @Override
    public CreationResponse createNewDriverLicense(DriverLicenseCreationRequest driverLicenseCreationRequest, Long userID) {

        Optional<Users> userCandidate = usersRepository.findById(userID);
        if (userCandidate.isEmpty()) {
            throw new ApiRequestException("Пользователь, создавший запрос, не был найден");
        }

        final String driverLicenseNumber = driverLicenseCreationRequest.getDriverLicenseNumber();
        final String driverLicenseCategory = driverLicenseCreationRequest.getDriverLicenseCategory();
        final Date driverLicenseDateOfIssue = driverLicenseCreationRequest.getDriverLicenseDateOfIssue();

        Optional<DriverLicenseCategory> driverLicenseCategoryCandidate = driverLicenseCategoryRepository.findByDriverLicenseCategory(driverLicenseCategory);

        if (driverLicenseCategoryCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        DriverLicenseCategory driverLicenseCategoryEntity = driverLicenseCategoryCandidate.get();

        DriverLicense driverLicense = DriverLicense
                .builder()
                .driverLicenseNumber(driverLicenseNumber)
                .driverLicenseCategory(driverLicenseCategoryEntity)
                .driverLicenseDateOfIssue(driverLicenseDateOfIssue)
                .build();

        driverLicenseRepository.save(driverLicense);

        Optional<DriverLicense> createdDriverLicense = driverLicenseRepository.findByDriverLicenseNumber(driverLicenseNumber);

        if (createdDriverLicense.isEmpty()) {
            throw new InternalApiException("Не удалось добавить водительские права");
        }

        return new CreationResponse("DriverLicense", createdDriverLicense.get().getId());
    }

    @Override
    public DriverLicense getDriverLicense(Long userID) {
        return findDriverLicenseByUserID(userID);
    }

    @Override
    public String updateDriverLicense(DriverLicenseUpdateRequest driverLicenseUpdateRequest, Long userID) {

        final String updatedDriverLicenseNumber = driverLicenseUpdateRequest.getUpdatedDriverLicenseNumber();
        final String updatedDriverLicenseCategory = driverLicenseUpdateRequest.getUpdatedDriverLicenseCategory();
        final Date updatedDriverLicenseDateOfIssue = driverLicenseUpdateRequest.getUpdatedDriverLicenseDateOfIssue();

        Optional<DriverLicenseCategory> driverLicenseCategoryCandidate = driverLicenseCategoryRepository.findByDriverLicenseCategory(updatedDriverLicenseCategory);

        if (driverLicenseCategoryCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        final DriverLicenseCategory updatedDriverLicenseCategoryEntity = driverLicenseCategoryCandidate.get();

        DriverLicense driverLicense = findDriverLicenseByUserID(userID);
        driverLicense.setDriverLicenseNumber(updatedDriverLicenseNumber);
        driverLicense.setDriverLicenseCategory(updatedDriverLicenseCategoryEntity);
        driverLicense.setDriverLicenseDateOfIssue(updatedDriverLicenseDateOfIssue);

        driverLicenseRepository.save(driverLicense);
        return "Водительские права были изменены";
    }

    @Override
    public String deleteDriverLicense(Long userID) {
        DriverLicense driverLicense = findDriverLicenseByUserID(userID);
        driverLicenseRepository.delete(driverLicense);
        return "Водительские права были удалены";
    }

    private DriverLicense findDriverLicenseByUserID(Long userID) {
        Optional<Users> userCandidate = usersRepository.findById(userID);

        if (userCandidate.isEmpty()) {
            throw new ApiRequestException("Пользователь не был найден");
        }

        Users user = userCandidate.get();

        Optional<UserProfile> userProfileCandidate = userProfileRepository.findByUser(user);

        if (userProfileCandidate.isEmpty()) {
            throw new ApiRequestException("Профиль пользователя не был найден");
        }

        UserProfile userProfile = userProfileCandidate.get();
        return userProfile.getUserDriverLicense();
    }
}
