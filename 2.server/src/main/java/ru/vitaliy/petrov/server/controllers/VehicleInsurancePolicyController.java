package ru.vitaliy.petrov.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vitaliy.petrov.server.error.ApiRequestException;
import ru.vitaliy.petrov.server.forms.requests.userprofile.VehicleInsurancePolicyCreationRequest;
import ru.vitaliy.petrov.server.forms.requests.userprofile.VehicleInsurancePolicyUpdateRequest;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.forms.responses.StringResponse;
import ru.vitaliy.petrov.server.models.VehicleInsurancePolicy;
import ru.vitaliy.petrov.server.security.JwtUtil;
import ru.vitaliy.petrov.server.services.VehicleInsurancePolicyService;

import javax.validation.Valid;

@RestController
public class VehicleInsurancePolicyController {

    @Autowired
    private VehicleInsurancePolicyService vehicleInsurancePolicyService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/vehicle-insurance-policy/create")
    public CreationResponse createNewVehicleInsurancePolicy(@Valid VehicleInsurancePolicyCreationRequest vehicleInsurancePolicyCreationRequest, BindingResult bindingResult, @RequestHeader("Authorization") String jwtToken) {
        if(bindingResult.hasErrors())
            throw new ApiRequestException("Введенные данные неверны");
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return vehicleInsurancePolicyService.createNewVehicleInsurancePolicy(vehicleInsurancePolicyCreationRequest, userID);
    }

    @GetMapping("/vehicle-insurance-policy/get")
    public VehicleInsurancePolicy getVehicleInsurancePolicy(Long policyID) {
        return vehicleInsurancePolicyService.getVehicleInsurancePolicy(policyID);
    }

    @PatchMapping("/vehicle-insurance-policy/update")
    public StringResponse updateVehicleInsurancePolicy(@Valid VehicleInsurancePolicyUpdateRequest vehicleInsurancePolicyUpdateRequest, BindingResult bindingResult, Long policyID) {
        if(bindingResult.hasErrors())
            throw new ApiRequestException("Введенные данные неверны");
        return new StringResponse(vehicleInsurancePolicyService.updateVehicleInsurancePolicy(vehicleInsurancePolicyUpdateRequest, policyID));
    }

    @DeleteMapping("/vehicle-insurance-policy/delete")
    public StringResponse deleteVehicleInsurancePolicy(Long policyID) {
        return new StringResponse(vehicleInsurancePolicyService.deleteVehicleInsurancePolicy(policyID));
    }

}
