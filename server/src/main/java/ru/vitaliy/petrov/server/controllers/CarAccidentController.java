package ru.vitaliy.petrov.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vitaliy.petrov.server.dto.*;
import ru.vitaliy.petrov.server.forms.requests.caraccident.*;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.forms.responses.StringResponse;
import ru.vitaliy.petrov.server.models.*;
import ru.vitaliy.petrov.server.security.JwtUtil;
import ru.vitaliy.petrov.server.services.CarAccidentService;
import ru.vitaliy.petrov.server.services.UserProfileService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@RestController
public class CarAccidentController {

    @Autowired
    private CarAccidentService carAccidentService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping("/car-accident/create")
    public CreationResponse createNewCarAccidentWithChats(@RequestBody CarAccidentCreationRequest carAccidentCreationRequest, @RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return carAccidentService.createNewCarAccidentWithChats(carAccidentCreationRequest, userID);
    }

    @GetMapping("/car-accident/get")
    public CarAccidentDTO getCarAccident(@RequestHeader("Authorization") String jwtToken, Long carAccidentID) {
        jwtUtil.extractAllClaimsFromHeader(jwtToken);
        return new CarAccidentDTO(carAccidentService.getCarAccident(carAccidentID));
    }

    @GetMapping("/car-accident/get-changelog")
    public List<CarAccidentEntityChangelogDTO> getCarAccidentChangelog(@RequestHeader("Authorization") String jwtToken, Long entityID, Date changeDate) {
        ArrayList<CarAccidentEntityChangelogDTO> result = new ArrayList<>();
        String userRole = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("role", String.class);
        List<CarAccidentEntityChangelog> carAccidentEntityChangelogs = carAccidentService.getChangelogOfAccident(entityID, changeDate, userRole);
        for (CarAccidentEntityChangelog carAccidentEntityChangelog : carAccidentEntityChangelogs) {
            result.add(new CarAccidentEntityChangelogDTO(carAccidentEntityChangelog));
        }
        return result;
    }

    @PatchMapping("/car-accident/update")
    public StringResponse updateCarAccident(@RequestHeader("Authorization") String jwtToken, CarAccidentUpdateRequest carAccidentUpdateRequest, Long carAccidentID) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return new StringResponse(carAccidentService.updateCarAccident(carAccidentUpdateRequest, carAccidentID));
    }

    @DeleteMapping("/car-accident/delete")
    public StringResponse deleteCarAccident(@RequestHeader("Authorization") String jwtToken, Long carAccidentID) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return new StringResponse(carAccidentService.deleteCarAccident(carAccidentID));
    }

    @PostMapping("/car-accident/participants/add")
    public CreationResponse addCarAccidentParticipant(CarAccidentParticipantAddRequest carAccidentParticipantAddRequest, @RequestHeader("Authorization") String jwtToken) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return carAccidentService.addCarAccidentParticipant(carAccidentParticipantAddRequest);
    }

    @PostMapping("/car-accident/witnesses/add")
    public CreationResponse addCarAccidentWitness(@RequestHeader("Authorization") String jwtToken, CarAccidentWitnessAddRequest carAccidentWitnessAddRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return carAccidentService.addCarAccidentWitness(carAccidentWitnessAddRequest);
    }

    @DeleteMapping("/car-accident/participants/delete")
    public StringResponse deleteCarAccidentParticipant(@RequestHeader("Authorization") String jwtToken, CarAccidentParticipantDeleteRequest carAccidentParticipantDeleteRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return new StringResponse(carAccidentService.deleteCarAccidentParticipant(carAccidentParticipantDeleteRequest));
    }

    @DeleteMapping("/car-accident/witnesses/delete")
    public StringResponse deleteCarAccidentWitness(@RequestHeader("Authorization") String jwtToken, CarAccidentWitnessDeleteRequest carAccidentWitnessDeleteRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return new StringResponse(carAccidentService.deleteCarAccidentWitness(carAccidentWitnessDeleteRequest));
    }

    @GetMapping("/car-accident/participants/get")
    public List<CarAccidentParticipantDTO> getCarAccidentParticipants(@RequestHeader("Authorization") String jwtToken, Long entityID) {
        jwtUtil.extractAllClaimsFromHeader(jwtToken);
        List<CarAccidentParticipant> res = carAccidentService.getCarAccidentParticipants(entityID);
        ArrayList<CarAccidentParticipantDTO> result = new ArrayList<>();
        for (CarAccidentParticipant participant : res) {
            UserProfile userProfile = userProfileService.getUserProfile(participant.getCarAccidentParticipant().getId());
            result.add(new CarAccidentParticipantDTO(participant, userProfile));
        }
        return result;
    }

    @GetMapping("/car-accident/witnesses/get")
    public List<CarAccidentWitnessDTO> getCarAccidentWitnesses(@RequestHeader("Authorization") String jwtToken, Long entityID) {
        jwtUtil.extractAllClaimsFromHeader(jwtToken);
        List<CarAccidentWitness> res = carAccidentService.getCarAccidentWitnesses(entityID);
        ArrayList<CarAccidentWitnessDTO> result = new ArrayList<>();
        for (CarAccidentWitness witness : res) {
            result.add(new CarAccidentWitnessDTO(witness));
        }
        return result;
    }

    @PatchMapping("/car-accident/witnesses/update")
    public StringResponse updateCarAccidentWitness(@RequestHeader("Authorization") String jwtToken, CarAccidentWitnessUpdateRequest carAccidentWitnessUpdateRequest) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return new StringResponse(carAccidentService.updateCarAccidentWitness(carAccidentWitnessUpdateRequest));
    }

    @GetMapping("/car-accident/info/get")
    public CarAccidentEntityDTO getCarAccidentInfo(@RequestHeader("Authorization") String jwtToken, CarAccidentInfoRequest carAccidentInfoRequest) {
        jwtUtil.extractAllClaimsFromHeader(jwtToken);
        return new CarAccidentEntityDTO(carAccidentService.getCarAccidentInfo(carAccidentInfoRequest));
    }

    @GetMapping("/car-accident/info/get-all-user")
    public List<CarAccidentEntityDTO> getUsersCarAccidentsInfos(@RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        ArrayList<CarAccidentEntityDTO> result = new ArrayList<>();
        List<CarAccidentEntity> carAccidentEntities = carAccidentService.getUsersCarAccidentsInfo(userID);
        for (CarAccidentEntity carAccidentEntity : carAccidentEntities) {
            result.add(new CarAccidentEntityDTO(carAccidentEntity));
        }
        return result;
    }

    @GetMapping("/car-accident/info/get-all-officer")
    public List<CarAccidentEntityDTO> getOfficersCarAccidentsInfos(@RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        ArrayList<CarAccidentEntityDTO> result = new ArrayList<>();
        List<CarAccidentEntity> carAccidentEntities = carAccidentService.getOfficersCarAccidentsInfo(userID);
        for (CarAccidentEntity carAccidentEntity : carAccidentEntities) {
            result.add(new CarAccidentEntityDTO(carAccidentEntity));
        }
        return result;
    }

    @PatchMapping("/car-accident/change-state")
    public StringResponse changeCarAccidentState(@RequestHeader("Authorization") String jwtToken, Long entityID, String entityState) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        return carAccidentService.changeEntityState(entityID, entityState);
    }

}
