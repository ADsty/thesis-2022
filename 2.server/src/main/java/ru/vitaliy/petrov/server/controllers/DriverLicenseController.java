package ru.vitaliy.petrov.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vitaliy.petrov.server.error.ApiRequestException;
import ru.vitaliy.petrov.server.forms.requests.userprofile.DriverLicenseCreationRequest;
import ru.vitaliy.petrov.server.forms.requests.userprofile.DriverLicenseUpdateRequest;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.forms.responses.StringResponse;
import ru.vitaliy.petrov.server.models.DriverLicense;
import ru.vitaliy.petrov.server.security.JwtUtil;
import ru.vitaliy.petrov.server.services.DriverLicenseService;

import javax.validation.Valid;

@RestController
public class DriverLicenseController {

    @Autowired
    private DriverLicenseService driverLicenseService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/driver-license/create")
    public CreationResponse createNewDriverLicense(@Valid DriverLicenseCreationRequest driverLicenseCreationRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            throw new ApiRequestException("Введенные данные неверны");
        return driverLicenseService.createNewDriverLicense(driverLicenseCreationRequest);
    }

    @GetMapping("/driver-license/get")
    public DriverLicense getDriverLicense(@RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return driverLicenseService.getDriverLicense(userID);
    }

    @PatchMapping("/driver-license/update")
    public StringResponse updateDriverLicense(@Valid DriverLicenseUpdateRequest driverLicenseUpdateRequest, BindingResult bindingResult, @RequestHeader("Authorization") String jwtToken) {
        if(bindingResult.hasErrors())
            throw new ApiRequestException("Введенные данные неверны");
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return new StringResponse(driverLicenseService.updateDriverLicense(driverLicenseUpdateRequest, userID));
    }

    @DeleteMapping("/driver-license/delete")
    public StringResponse deleteDriverLicense(@RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return new StringResponse(driverLicenseService.deleteDriverLicense(userID));
    }

}
