package ru.vitaliy.petrov.server.services;

import ru.vitaliy.petrov.server.forms.requests.userprofile.VehicleProfileCreationRequest;
import ru.vitaliy.petrov.server.forms.requests.userprofile.VehicleProfileUpdateRequest;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.models.VehicleProfile;

import java.util.List;

public interface IVehicleProfileService {

    CreationResponse createNewVehicleProfile(VehicleProfileCreationRequest vehicleProfileCreationRequest, Long userID);

    VehicleProfile getVehicleProfile(Long vehicleID);

    List<VehicleProfile> getAllVehicleProfiles(Long userID);

    String updateVehicleProfile(VehicleProfileUpdateRequest vehicleProfileUpdateRequest, Long vehicleID);

    String deleteVehicleProfile(Long vehicleID);

}
