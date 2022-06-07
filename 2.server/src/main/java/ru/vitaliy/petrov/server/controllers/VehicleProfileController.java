package ru.vitaliy.petrov.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vitaliy.petrov.server.dto.VehicleProfileDTO;
import ru.vitaliy.petrov.server.error.ApiRequestException;
import ru.vitaliy.petrov.server.forms.requests.userprofile.VehicleProfileCreationRequest;
import ru.vitaliy.petrov.server.forms.requests.userprofile.VehicleProfileUpdateRequest;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.forms.responses.StringResponse;
import ru.vitaliy.petrov.server.models.VehicleProfile;
import ru.vitaliy.petrov.server.security.JwtUtil;
import ru.vitaliy.petrov.server.services.VehicleProfileService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class VehicleProfileController {

    @Autowired
    private VehicleProfileService vehicleProfileService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/vehicle-profile/create")
    public CreationResponse createNewVehicleProfile(@Valid VehicleProfileCreationRequest vehicleProfileCreationRequest, BindingResult bindingResult, @RequestHeader("Authorization") String jwtToken) {
        if (bindingResult.hasErrors())
            throw new ApiRequestException("Введенные данные неверны");
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return vehicleProfileService.createNewVehicleProfile(vehicleProfileCreationRequest, userID);
    }

    @GetMapping("/vehicle-profile/get")
    public List<VehicleProfileDTO> getUserVehicleProfiles(@RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        ArrayList<VehicleProfileDTO> result = new ArrayList<>();
        List<VehicleProfile> vehicleProfiles = vehicleProfileService.getAllVehicleProfiles(userID);
        for (VehicleProfile vehicleProfile : vehicleProfiles) {
            result.add(new VehicleProfileDTO(vehicleProfile));
        }
        return result;
    }

    @PatchMapping("/vehicle-profile/update")
    public StringResponse updateVehicleProfile(@RequestHeader("Authorization") String jwtToken, @Valid VehicleProfileUpdateRequest vehicleProfileUpdateRequest, BindingResult bindingResult, Long vehicleID) {
        if (bindingResult.hasErrors())
            throw new ApiRequestException("Введенные данные неверны");
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return new StringResponse(vehicleProfileService.updateVehicleProfile(vehicleProfileUpdateRequest, vehicleID, userID));
    }

    @DeleteMapping("/vehicle-profile/delete")
    public StringResponse deleteVehicleProfile(@RequestHeader("Authorization") String jwtToken, Long vehicleID) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return new StringResponse(vehicleProfileService.deleteVehicleProfile(vehicleID, userID));
    }

}