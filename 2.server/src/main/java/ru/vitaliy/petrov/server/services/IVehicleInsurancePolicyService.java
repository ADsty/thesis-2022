package ru.vitaliy.petrov.server.services;

import ru.vitaliy.petrov.server.forms.requests.userprofile.VehicleInsurancePolicyCreationRequest;
import ru.vitaliy.petrov.server.forms.requests.userprofile.VehicleInsurancePolicyUpdateRequest;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.models.VehicleInsurancePolicy;

public interface IVehicleInsurancePolicyService {
    CreationResponse createNewVehicleInsurancePolicy(VehicleInsurancePolicyCreationRequest vehicleInsurancePolicyCreationRequest, Long userID);

    VehicleInsurancePolicy getVehicleInsurancePolicy(Long policyID, Long userID);

    String updateVehicleInsurancePolicy(VehicleInsurancePolicyUpdateRequest vehicleInsurancePolicyUpdateRequest, Long policyID, Long userID);

    String deleteVehicleInsurancePolicy(Long policyID, Long userID);
}
