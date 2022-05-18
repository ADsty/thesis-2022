package ru.vitaliy.petrov.server.services;

import ru.vitaliy.petrov.server.forms.requests.*;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.models.CarAccident;
import ru.vitaliy.petrov.server.models.CarAccidentEntity;

import java.util.List;

public interface ICarAccidentService {

    CreationResponse createNewCarAccidentWithChats(CarAccidentCreationRequest carAccidentCreationRequest, Long userID);

    CarAccident getCarAccident(Long carAccidentID);

    String updateCarAccident(CarAccidentUpdateRequest carAccidentUpdateRequest, Long carAccidentID);

    String deleteCarAccident(Long carAccidentID);

    CreationResponse addCarAccidentParticipant(CarAccidentParticipantAddRequest carAccidentParticipantAddRequest);

    CreationResponse addCarAccidentWitness(CarAccidentWitnessAddRequest carAccidentWitnessAddRequest);

    String deleteCarAccidentParticipant(CarAccidentParticipantDeleteRequest carAccidentParticipantDeleteRequest);

    String deleteCarAccidentWitness(CarAccidentWitnessDeleteRequest carAccidentWitnessDeleteRequest);

    String updateCarAccidentWitness(CarAccidentWitnessUpdateRequest carAccidentWitnessUpdateRequest);

    CreationResponse synchronizeWithExistingCarAccidentEntity(CarAccidentCreationRequest carAccidentCreationRequest);

    List<CarAccidentEntity> getUsersCarAccidentsInfo(Long userID);

    CarAccidentEntity getCarAccidentInfo(CarAccidentInfoRequest carAccidentInfoRequest);
}
