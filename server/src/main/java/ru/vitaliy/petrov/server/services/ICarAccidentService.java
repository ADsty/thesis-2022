package ru.vitaliy.petrov.server.services;

import ru.vitaliy.petrov.server.forms.requests.caraccident.*;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.models.*;

import java.util.ArrayList;
import java.util.List;

public interface ICarAccidentService {

    CreationResponse createNewCarAccidentWithChats(CarAccidentCreationRequest carAccidentCreationRequest, Long userID);

    CarAccident getCarAccident(Long carAccidentID);

    String updateCarAccident(CarAccidentUpdateRequest carAccidentUpdateRequest, Long carAccidentID);

    String deleteCarAccident(Long carAccidentID);

    CreationResponse addCarAccidentParticipant(CarAccidentParticipantAddRequest carAccidentParticipantAddRequest);

    CreationResponse addCarAccidentWitness(CarAccidentWitnessAddRequest carAccidentWitnessAddRequest);

    List<CarAccidentParticipant> getCarAccidentParticipants(Long entityID);

    List<CarAccidentWitness> getCarAccidentWitnesses(Long entityID);

    String deleteCarAccidentParticipant(CarAccidentParticipantDeleteRequest carAccidentParticipantDeleteRequest);

    String deleteCarAccidentWitness(CarAccidentWitnessDeleteRequest carAccidentWitnessDeleteRequest);

    String updateCarAccidentWitness(CarAccidentWitnessUpdateRequest carAccidentWitnessUpdateRequest);

    CreationResponse synchronizeWithExistingCarAccidentEntity(ArrayList<Users> userList, List<Long> vehicles, Long entityID);

    List<CarAccidentEntity> getUsersCarAccidentsInfo(Long userID);

    CarAccidentEntity getCarAccidentInfo(CarAccidentInfoRequest carAccidentInfoRequest);
}
