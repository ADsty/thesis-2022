package ru.vitaliy.petrov.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vitaliy.petrov.server.forms.requests.caraccident.*;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.forms.responses.StringResponse;
import ru.vitaliy.petrov.server.models.CarAccident;
import ru.vitaliy.petrov.server.models.CarAccidentEntity;
import ru.vitaliy.petrov.server.security.JwtUtil;
import ru.vitaliy.petrov.server.services.CarAccidentService;

import java.util.List;


@RestController
public class CarAccidentController {

    @Autowired
    private CarAccidentService carAccidentService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/car-accident/create")
    public CreationResponse createNewCarAccidentWithChats(@RequestBody CarAccidentCreationRequest carAccidentCreationRequest, @RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return carAccidentService.createNewCarAccidentWithChats(carAccidentCreationRequest, userID);
    }

    @GetMapping("/car-accident/get")
    public CarAccident getCarAccident(Long carAccidentID) {
        return carAccidentService.getCarAccident(carAccidentID);
    }

    @PatchMapping("/car-accident/update")
    public StringResponse updateCarAccident(CarAccidentUpdateRequest carAccidentUpdateRequest, Long carAccidentID) {
        return new StringResponse(carAccidentService.updateCarAccident(carAccidentUpdateRequest, carAccidentID));
    }

    @DeleteMapping("/car-accident/delete")
    public StringResponse deleteCarAccident(Long carAccidentID) {
        return new StringResponse(carAccidentService.deleteCarAccident(carAccidentID));
    }

    @PostMapping("/car-accident/participants/add")
    public CreationResponse addCarAccidentParticipant(CarAccidentParticipantAddRequest carAccidentParticipantAddRequest) {
        return carAccidentService.addCarAccidentParticipant(carAccidentParticipantAddRequest);
    }

    @PostMapping("/car-accident/witnessses/add")
    public CreationResponse addCarAccidentWitness(CarAccidentWitnessAddRequest carAccidentWitnessAddRequest) {
        return carAccidentService.addCarAccidentWitness(carAccidentWitnessAddRequest);
    }

    @DeleteMapping("/car-accident/participants/delete")
    public StringResponse deleteCarAccidentParticipant(CarAccidentParticipantDeleteRequest carAccidentParticipantDeleteRequest) {
        return new StringResponse(carAccidentService.deleteCarAccidentParticipant(carAccidentParticipantDeleteRequest));
    }

    @DeleteMapping("/car-accident/witnesses/delete")
    public StringResponse deleteCarAccidentWitness(CarAccidentWitnessDeleteRequest carAccidentWitnessDeleteRequest) {
        return new StringResponse(carAccidentService.deleteCarAccidentWitness(carAccidentWitnessDeleteRequest));
    }

    @GetMapping("/car-accident/info/get")
    public CarAccidentEntity getCarAccidentInfo(CarAccidentInfoRequest carAccidentInfoRequest) {
        return carAccidentService.getCarAccidentInfo(carAccidentInfoRequest);
    }

    @GetMapping("/car-accident/info/get-all")
    public List<CarAccidentEntity> getUsersCarAccidentsInfos(@RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return carAccidentService.getUsersCarAccidentsInfo(userID);
    }

}
