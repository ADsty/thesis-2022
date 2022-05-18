package ru.vitaliy.petrov.server.services;

import ru.vitaliy.petrov.server.forms.requests.DriverLicenseCreationRequest;
import ru.vitaliy.petrov.server.forms.requests.DriverLicenseUpdateRequest;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.models.DriverLicense;

public interface IDriverLicenseService {

    CreationResponse createNewDriverLicense(DriverLicenseCreationRequest driverLicenseCreationRequest);

    DriverLicense getDriverLicense(Long userID);

    String updateDriverLicense(DriverLicenseUpdateRequest driverLicenseUpdateRequest, Long userID);

    String deleteDriverLicense(Long userID);
}
