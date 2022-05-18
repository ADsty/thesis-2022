package ru.vitaliy.petrov.server.services;

import org.springframework.stereotype.Service;
import ru.vitaliy.petrov.server.forms.requests.*;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.models.CarAccident;
import ru.vitaliy.petrov.server.models.CarAccidentEntity;

import java.util.List;

@Service
public class CarAccidentService implements ICarAccidentService {

    @Override
    public CreationResponse createNewCarAccidentWithChats(CarAccidentCreationRequest carAccidentCreationRequest, Long userID) {
        return null;
    }

    @Override
    public CarAccident getCarAccident(Long carAccidentID) {
        return null;
    }

    @Override
    public String updateCarAccident(CarAccidentUpdateRequest carAccidentUpdateRequest, Long carAccidentID) {
        return null;
    }

    @Override
    public String deleteCarAccident(Long carAccidentID) {
        return null;
    }

    @Override
    public CreationResponse addCarAccidentParticipant(CarAccidentParticipantAddRequest carAccidentParticipantAddRequest) {
        return null;
    }

    @Override
    public CreationResponse addCarAccidentWitness(CarAccidentWitnessAddRequest carAccidentWitnessAddRequest) {
        return null;
    }

    @Override
    public String deleteCarAccidentParticipant(CarAccidentParticipantDeleteRequest carAccidentParticipantDeleteRequest) {
        return null;
    }

    @Override
    public String deleteCarAccidentWitness(CarAccidentWitnessDeleteRequest carAccidentWitnessDeleteRequest) {
        return null;
    }

    @Override
    public String updateCarAccidentWitness(CarAccidentWitnessUpdateRequest carAccidentWitnessUpdateRequest) {
        return null;
    }

    @Override
    public CreationResponse synchronizeWithExistingCarAccidentEntity(CarAccidentCreationRequest carAccidentCreationRequest) {
        return null;
    }

    @Override
    public List<CarAccidentEntity> getUsersCarAccidentsInfo(Long userID) {
        return null;
    }

    @Override
    public CarAccidentEntity getCarAccidentInfo(CarAccidentInfoRequest carAccidentInfoRequest) {
        return null;
    }

}
