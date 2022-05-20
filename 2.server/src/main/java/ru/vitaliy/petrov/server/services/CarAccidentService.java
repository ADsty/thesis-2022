package ru.vitaliy.petrov.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vitaliy.petrov.server.error.ApiRequestException;
import ru.vitaliy.petrov.server.error.InternalApiException;
import ru.vitaliy.petrov.server.forms.requests.*;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.models.*;
import ru.vitaliy.petrov.server.repositories.*;

import java.util.List;
import java.util.Optional;

@Service
public class CarAccidentService implements ICarAccidentService {

    @Autowired
    private CarAccidentRepository carAccidentRepository;

    @Autowired
    private CarAccidentEntityRepository carAccidentEntityRepository;

    @Autowired
    private CarAccidentParticipantRepository carAccidentParticipantRepository;

    @Autowired
    private CarAccidentWitnessRepository carAccidentWitnessRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public CarAccident getCarAccident(Long carAccidentID) {
        return getAccident(carAccidentID);
    }

    @Override
    public String updateCarAccident(CarAccidentUpdateRequest carAccidentUpdateRequest, Long carAccidentID) {
        CarAccident carAccident = getCarAccident(carAccidentID);

        final String updatedCarAccidentScene = carAccidentUpdateRequest.getUpdatedCarAccidentScene();
        final String updatedCarAccidentDate = carAccidentUpdateRequest.getUpdatedCarAccidentDate();
        final String updatedCarAccidentTime = carAccidentUpdateRequest.getUpdatedCarAccidentTime();
        final Integer updatedCarAccidentParticipantsNumber = carAccidentUpdateRequest.getUpdatedCarAccidentParticipantsNumber();
        final Integer updatedCarAccidentWitnessesNumber = carAccidentUpdateRequest.getUpdatedCarAccidentWitnessesNumber();

        if(updatedCarAccidentScene == null && updatedCarAccidentDate == null && updatedCarAccidentTime == null && updatedCarAccidentParticipantsNumber == null && updatedCarAccidentWitnessesNumber == null) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        if(updatedCarAccidentScene != null) {
            carAccident.setCarAccidentScene(updatedCarAccidentScene);
        }

        if(updatedCarAccidentDate != null) {
            carAccident.setCarAccidentDate(updatedCarAccidentDate);
        }

        if(updatedCarAccidentTime != null) {
            carAccident.setCarAccidentTime(updatedCarAccidentTime);
        }

        if(updatedCarAccidentParticipantsNumber != null) {
            carAccident.setCarAccidentParticipantsNumber(updatedCarAccidentParticipantsNumber);
        }

        if(updatedCarAccidentWitnessesNumber != null) {
            carAccident.setCarAccidentWitnessesNumber(updatedCarAccidentWitnessesNumber);
        }

        carAccidentRepository.save(carAccident);
        return "ДТП было обновлено";
    }

    @Override
    public String deleteCarAccident(Long carAccidentID) {
        CarAccident carAccident = getCarAccident(carAccidentID);
        carAccidentRepository.delete(carAccident);
        return "ДТП было удалено";
    }

    @Override
    public CreationResponse addCarAccidentParticipant(CarAccidentParticipantAddRequest carAccidentParticipantAddRequest) {
        final Long entityID = carAccidentParticipantAddRequest.getEntityID();
        final Long participantID = carAccidentParticipantAddRequest.getParticipantID();

        final Users participant = usersRepository.getById(participantID);
        final CarAccidentEntity carAccidentEntity = getEntity(entityID);

        CarAccidentParticipant carAccidentParticipant = CarAccidentParticipant
                .builder()
                .carAccidentEntity(carAccidentEntity)
                .carAccidentParticipant(participant)
                .build();

        carAccidentParticipantRepository.save(carAccidentParticipant);
        Optional<CarAccidentParticipant> createdParticipant = carAccidentParticipantRepository.findByCarAccidentParticipantAndCarAccidentEntity(participant, carAccidentEntity);

        if(createdParticipant.isEmpty()) {
            throw new InternalApiException("Не удалось добавить участника ДТП");
        }

        return new CreationResponse("CarAccidentParticipant", createdParticipant.get().getId());
    }

    @Override
    public CreationResponse addCarAccidentWitness(CarAccidentWitnessAddRequest carAccidentWitnessAddRequest) {
        final Long entityID = carAccidentWitnessAddRequest.getEntityID();
        final String witnessFullName = carAccidentWitnessAddRequest.getWitnessFullName();
        final String witnessResidentialAddress = carAccidentWitnessAddRequest.getWitnessResidentialAddress();
        final String witnessPhoneNumber = carAccidentWitnessAddRequest.getWitnessPhoneNumber();

        if(entityID == null || witnessFullName == null || witnessResidentialAddress == null || witnessPhoneNumber == null) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        final CarAccidentEntity carAccidentEntity = getEntity(entityID);

        CarAccidentWitness carAccidentWitness = CarAccidentWitness
                .builder()
                .carAccidentEntity(carAccidentEntity)
                .witnessFullName(witnessFullName)
                .witnessResidentialAddress(witnessResidentialAddress)
                .witnessPhoneNumber(witnessPhoneNumber)
                .build();

        carAccidentWitnessRepository.save(carAccidentWitness);

        Optional<CarAccidentWitness> createdWitness = carAccidentWitnessRepository.findByCarAccidentEntityAndWitnessFullName(carAccidentEntity, witnessFullName);

        if(createdWitness.isEmpty()) {
            throw new InternalApiException("Не удалось добавить свидетеля ДТП");
        }

        return new CreationResponse("CarAccidentWitness", createdWitness.get().getId());
    }

    @Override
    public String deleteCarAccidentParticipant(CarAccidentParticipantDeleteRequest carAccidentParticipantDeleteRequest) {

        final Long participantID = carAccidentParticipantDeleteRequest.getParticipantID();

        if(participantID == null) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        carAccidentParticipantRepository.delete(getParticipant(participantID));
        return "Участник ДТП удален";
    }

    @Override
    public String deleteCarAccidentWitness(CarAccidentWitnessDeleteRequest carAccidentWitnessDeleteRequest) {
        final Long witnessID = carAccidentWitnessDeleteRequest.getWitnessID();

        if(witnessID == null) {
            throw new ApiRequestException("Введенные данные неверны");
        }
        carAccidentWitnessRepository.delete(getWitness(witnessID));
        return "Свидетель ДТП удален";
    }

    @Override
    public String updateCarAccidentWitness(CarAccidentWitnessUpdateRequest carAccidentWitnessUpdateRequest) {
        final Long witnessID = carAccidentWitnessUpdateRequest.getWitnessID();
        final String updatedWitnessFullName = carAccidentWitnessUpdateRequest.getUpdatedWitnessFullName();
        final String updatedWitnessResidentialAddress = carAccidentWitnessUpdateRequest.getUpdatedWitnessResidentialAddress();
        final String updatedWitnessPhoneNumber = carAccidentWitnessUpdateRequest.getUpdatedWitnessPhoneNumber();

        if(witnessID == null && updatedWitnessFullName == null && updatedWitnessResidentialAddress == null && updatedWitnessPhoneNumber == null) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        final CarAccidentWitness witness = getWitness(witnessID);

        if(updatedWitnessFullName != null) {
            witness.setWitnessFullName(updatedWitnessFullName);
        }

        if(updatedWitnessResidentialAddress != null) {
            witness.setWitnessResidentialAddress(updatedWitnessResidentialAddress);
        }

        if(updatedWitnessPhoneNumber != null) {
            witness.setWitnessPhoneNumber(updatedWitnessPhoneNumber);
        }

        carAccidentWitnessRepository.save(witness);
        return "Свидетель ДТП был обновлен";
    }

    private CarAccident getAccident(Long accidentID) {
        Optional<CarAccident> carAccidentCandidate = carAccidentRepository.findById(accidentID);

        if(carAccidentCandidate.isEmpty()) {
            throw new ApiRequestException("ДТП не найдено");
        }

        return carAccidentCandidate.get();
    }

    @Override
    public CreationResponse createNewCarAccidentWithChats(CarAccidentCreationRequest carAccidentCreationRequest, Long userID) {
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

    private CarAccidentEntity getEntity(Long entityID) {
        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(entityID);

        if(carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("ДТП не найдено");
        }

        return carAccidentEntityCandidate.get();
    }

    private CarAccidentParticipant getParticipant(Long participantID) {

        Optional<CarAccidentParticipant> carAccidentParticipantCandidate = carAccidentParticipantRepository.findById(participantID);

        if(carAccidentParticipantCandidate.isEmpty()) {
            throw new ApiRequestException("Участник ДТП не найден");
        }
        return carAccidentParticipantCandidate.get();
    }

    private CarAccidentWitness getWitness(Long witnessID) {
        Optional<CarAccidentWitness> carAccidentWitnessCandidate = carAccidentWitnessRepository.findById(witnessID);

        if(carAccidentWitnessCandidate.isEmpty()) {
            throw new ApiRequestException("Свидетель ДТП не найден");
        }
        return carAccidentWitnessCandidate.get();
    }

}
