package ru.vitaliy.petrov.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vitaliy.petrov.server.error.ApiRequestException;
import ru.vitaliy.petrov.server.error.InternalApiException;
import ru.vitaliy.petrov.server.forms.requests.DriverLicenseCreationRequest;
import ru.vitaliy.petrov.server.forms.requests.DriverLicenseUpdateRequest;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.models.DriverLicense;
import ru.vitaliy.petrov.server.models.DriverLicenseCategory;
import ru.vitaliy.petrov.server.models.UserProfile;
import ru.vitaliy.petrov.server.models.Users;
import ru.vitaliy.petrov.server.repositories.DriverLicenseCategoryRepository;
import ru.vitaliy.petrov.server.repositories.DriverLicenseRepository;
import ru.vitaliy.petrov.server.repositories.UserProfileRepository;
import ru.vitaliy.petrov.server.repositories.UsersRepository;

import java.util.Optional;

@Service
public class DriverLicenseService implements IDriverLicenseService{

    @Autowired
    private DriverLicenseRepository driverLicenseRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private DriverLicenseCategoryRepository driverLicenseCategoryRepository;

    @Override
    public CreationResponse createNewDriverLicense(DriverLicenseCreationRequest driverLicenseCreationRequest) {

        final String driverLicenseNumber = driverLicenseCreationRequest.getDriverLicenseNumber();
        final String driverLicenseCategory = driverLicenseCreationRequest.getDriverLicenseCategory();
        final String driverLicenseDateOfIssue = driverLicenseCreationRequest.getDriverLicenseDateOfIssue();

        Optional<DriverLicenseCategory> driverLicenseCategoryCandidate = driverLicenseCategoryRepository.findByDriverLicenseCategory(driverLicenseCategory);

        if(driverLicenseCategoryCandidate.isEmpty() || driverLicenseNumber == null || driverLicenseDateOfIssue == null) {
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

        if(createdDriverLicense.isEmpty()) {
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
        final String updatedDriverLicenseDateOfIssue = driverLicenseUpdateRequest.getUpdatedDriverLicenseDateOfIssue();

        if(updatedDriverLicenseNumber == null && updatedDriverLicenseCategory == null && updatedDriverLicenseDateOfIssue == null) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        DriverLicenseCategory updatedDriverLicenseCategoryEntity = null;

        Optional<DriverLicenseCategory> driverLicenseCategoryCandidate = driverLicenseCategoryRepository.findByDriverLicenseCategory(updatedDriverLicenseCategory);

        if(driverLicenseCategoryCandidate.isPresent()) {
            updatedDriverLicenseCategoryEntity = driverLicenseCategoryCandidate.get();
        }

        DriverLicense driverLicense = findDriverLicenseByUserID(userID);

        if(updatedDriverLicenseNumber != null) {
            driverLicense.setDriverLicenseNumber(updatedDriverLicenseNumber);
        }

        if(updatedDriverLicenseCategory != null) {
            driverLicense.setDriverLicenseCategory(updatedDriverLicenseCategoryEntity);
        }

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

        if(userProfileCandidate.isEmpty()) {
            throw new ApiRequestException("Профиль пользователя не был найден");
        }

        UserProfile userProfile = userProfileCandidate.get();
        return userProfile.getUserDriverLicense();
    }
}
