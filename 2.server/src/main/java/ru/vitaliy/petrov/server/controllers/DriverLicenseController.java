package ru.vitaliy.petrov.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vitaliy.petrov.server.forms.requests.DriverLicenseCreationRequest;
import ru.vitaliy.petrov.server.forms.requests.DriverLicenseUpdateRequest;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.forms.responses.StringResponse;
import ru.vitaliy.petrov.server.models.DriverLicense;
import ru.vitaliy.petrov.server.security.JwtUtil;
import ru.vitaliy.petrov.server.services.DriverLicenseService;

@RestController
public class DriverLicenseController {

    @Autowired
    private DriverLicenseService driverLicenseService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/driver-license/create")
    public CreationResponse createNewDriverLicense(DriverLicenseCreationRequest driverLicenseCreationRequest) {
        return driverLicenseService.createNewDriverLicense(driverLicenseCreationRequest);
    }

    @GetMapping("/driver-license/get")
    public DriverLicense getDriverLicense(@RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return driverLicenseService.getDriverLicense(userID);
    }

    @PatchMapping("/driver-license/update")
    public StringResponse updateDriverLicense(DriverLicenseUpdateRequest driverLicenseUpdateRequest, @RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return new StringResponse(driverLicenseService.updateDriverLicense(driverLicenseUpdateRequest, userID));
    }

    @DeleteMapping("/driver-license/delete")
    public StringResponse deleteDriverLicense(@RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return new StringResponse(driverLicenseService.deleteDriverLicense(userID));
    }

}
