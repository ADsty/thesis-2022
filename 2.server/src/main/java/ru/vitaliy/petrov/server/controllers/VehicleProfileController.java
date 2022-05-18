package ru.vitaliy.petrov.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vitaliy.petrov.server.forms.requests.VehicleProfileCreationRequest;
import ru.vitaliy.petrov.server.forms.requests.VehicleProfileUpdateRequest;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.forms.responses.StringResponse;
import ru.vitaliy.petrov.server.models.VehicleProfile;
import ru.vitaliy.petrov.server.security.JwtUtil;
import ru.vitaliy.petrov.server.services.VehicleProfileService;

import java.util.List;

@RestController
public class VehicleProfileController {

    @Autowired
    private VehicleProfileService vehicleProfileService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/vehicle-profile/create")
    public CreationResponse createNewVehicleProfile(VehicleProfileCreationRequest vehicleProfileCreationRequest, @RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return vehicleProfileService.createNewVehicleProfile(vehicleProfileCreationRequest, userID);
    }

    @GetMapping("/vehicle-profile/get")
    public List<VehicleProfile> getUserVehicleProfiles(@RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return vehicleProfileService.getAllVehicleProfiles(userID);
    }

    @PatchMapping("/vehicle-profile/update")
    public StringResponse updateVehicleProfile(VehicleProfileUpdateRequest vehicleProfileUpdateRequest, Long vehicleID) {
        return new StringResponse(vehicleProfileService.updateVehicleProfile(vehicleProfileUpdateRequest, vehicleID));
    }

    @DeleteMapping("/vehicle-profile/delete")
    public StringResponse deleteVehicleProfile(Long vehicleID) {
        return new StringResponse(vehicleProfileService.deleteVehicleProfile(vehicleID));
    }

}