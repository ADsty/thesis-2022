package ru.vitaliy.petrov.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vitaliy.petrov.server.error.ApiRequestException;
import ru.vitaliy.petrov.server.error.InternalApiException;
import ru.vitaliy.petrov.server.forms.requests.caraccident.*;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.forms.responses.StringResponse;
import ru.vitaliy.petrov.server.models.*;
import ru.vitaliy.petrov.server.repositories.*;

import java.util.ArrayList;
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

    @Autowired
    private CarAccidentEntityStateRepository carAccidentEntityStateRepository;

    @Autowired
    private UserStateRepository userStateRepository;

    @Autowired
    private ChatRepository chatRepository;

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

        carAccident.setCarAccidentScene(updatedCarAccidentScene);
        carAccident.setCarAccidentDate(updatedCarAccidentDate);
        carAccident.setCarAccidentTime(updatedCarAccidentTime);
        carAccident.setCarAccidentParticipantsNumber(updatedCarAccidentParticipantsNumber);
        carAccident.setCarAccidentWitnessesNumber(updatedCarAccidentWitnessesNumber);


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

        Chat chat = Chat
                .builder()
                .carAccidentEntity(carAccidentEntity)
                .carAccidentParticipant(participant)
                .trafficPoliceOfficer(carAccidentEntity.getTrafficPoliceOfficer())
                .build();

        chatRepository.save(chat);

        return new CreationResponse("CarAccidentParticipant", createdParticipant.get().getId());
    }

    @Override
    public CreationResponse addCarAccidentWitness(CarAccidentWitnessAddRequest carAccidentWitnessAddRequest) {
        final Long entityID = carAccidentWitnessAddRequest.getEntityID();
        final String witnessFullName = carAccidentWitnessAddRequest.getWitnessFullName();
        final String witnessResidentialAddress = carAccidentWitnessAddRequest.getWitnessResidentialAddress();
        final String witnessPhoneNumber = carAccidentWitnessAddRequest.getWitnessPhoneNumber();

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
        carAccidentParticipantRepository.delete(getParticipant(participantID));
        return "Участник ДТП удален";
    }

    @Override
    public String deleteCarAccidentWitness(CarAccidentWitnessDeleteRequest carAccidentWitnessDeleteRequest) {
        final Long witnessID = carAccidentWitnessDeleteRequest.getWitnessID();
        carAccidentWitnessRepository.delete(getWitness(witnessID));
        return "Свидетель ДТП удален";
    }

    @Override
    public String updateCarAccidentWitness(CarAccidentWitnessUpdateRequest carAccidentWitnessUpdateRequest) {
        final Long witnessID = carAccidentWitnessUpdateRequest.getWitnessID();
        final String updatedWitnessFullName = carAccidentWitnessUpdateRequest.getUpdatedWitnessFullName();
        final String updatedWitnessResidentialAddress = carAccidentWitnessUpdateRequest.getUpdatedWitnessResidentialAddress();
        final String updatedWitnessPhoneNumber = carAccidentWitnessUpdateRequest.getUpdatedWitnessPhoneNumber();

        final CarAccidentWitness witness = getWitness(witnessID);

        witness.setWitnessFullName(updatedWitnessFullName);
        witness.setWitnessResidentialAddress(updatedWitnessResidentialAddress);
        witness.setWitnessPhoneNumber(updatedWitnessPhoneNumber);

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
        boolean isAlreadyCreated = false;
        Long createdEntityID = null;
        ArrayList<Users> userList = new ArrayList<>();
        List<Long> participantsList = carAccidentCreationRequest.getCarAccidentSynchronizedParticipants();
        for(Long participantID: participantsList) {
            Optional<Users> userCandidate = usersRepository.findById(participantID);
            if(userCandidate.isEmpty()) {
                throw new ApiRequestException("Введенные данные пользователей неверны");
            }
            userList.add(userCandidate.get());
                Optional<CarAccidentParticipant> lastUserParticipation = carAccidentParticipantRepository.findTopByCarAccidentParticipantOrderByIdDesc(userCandidate.get());
                if (lastUserParticipation.isPresent()) {
                    String participationState = lastUserParticipation.get().getCarAccidentEntity().getEntityState().getCarAccidentEntityState();
                    if (participationState.equals("Необработанное заявление") || participationState.equals("Обработанное заявление")) {
                        isAlreadyCreated = true;
                        createdEntityID = lastUserParticipation.get().getCarAccidentEntity().getId();
                        userList.remove(userCandidate.get());
                    }
                }
        }
        if(isAlreadyCreated && createdEntityID != null) {
            return synchronizeWithExistingCarAccidentEntity(userList, createdEntityID);
        }

        final String carAccidentScene = carAccidentCreationRequest.getCarAccidentScene();
        final String carAccidentDate = carAccidentCreationRequest.getCarAccidentDate();
        final String carAccidentTime = carAccidentCreationRequest.getCarAccidentTime();
        final Integer carAccidentParticipantsNumber = carAccidentCreationRequest.getCarAccidentParticipantsNumber();
        final Integer carAccidentWitnessesNumber = carAccidentCreationRequest.getCarAccidentWitnessesNumber();

        CarAccident carAccident = CarAccident
                .builder()
                .carAccidentScene(carAccidentScene)
                .carAccidentDate(carAccidentDate)
                .carAccidentTime(carAccidentTime)
                .carAccidentParticipantsNumber(carAccidentParticipantsNumber)
                .carAccidentWitnessesNumber(carAccidentWitnessesNumber)
                .build();

        carAccidentRepository.save(carAccident);

        Optional<CarAccident> createdCarAccident = carAccidentRepository.findByCarAccidentSceneAndCarAccidentDateAndCarAccidentTime(carAccidentScene, carAccidentDate, carAccidentTime);
        if(createdCarAccident.isEmpty()) {
            throw new InternalApiException("Не удалось создать сущность ДТП");
        }

        Optional<CarAccidentEntityState> carAccidentEntityState = carAccidentEntityStateRepository.findByCarAccidentEntityState("Обработанное заявление");

        Optional<UserState> readyForWorkState = userStateRepository.findByUserStateName("Готов к работе");

        Optional<Users> trafficPoliceOfficer = usersRepository.findFirstByUserState(readyForWorkState.get());

        while(trafficPoliceOfficer.isEmpty()) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }

            trafficPoliceOfficer = usersRepository.findFirstByUserState(readyForWorkState.get());
        }

        CarAccidentEntity carAccidentEntity = CarAccidentEntity
                .builder()
                .carAccident(createdCarAccident.get())
                .entityState(carAccidentEntityState.get())
                .trafficPoliceOfficer(trafficPoliceOfficer.get())
                .build();

        carAccidentEntityRepository.save(carAccidentEntity);

        Optional<CarAccidentEntity> createdCarAccidentEntity = carAccidentEntityRepository.findByCarAccident(createdCarAccident.get());

        if(createdCarAccidentEntity.isEmpty()) {
            throw new InternalApiException("Не удалось создать сущность ДТП");
        }

        for(Users user: userList) {
            CarAccidentParticipant carAccidentParticipant = CarAccidentParticipant
                    .builder()
                    .carAccidentParticipant(user)
                    .carAccidentEntity(createdCarAccidentEntity.get())
                    .build();

            carAccidentParticipantRepository.save(carAccidentParticipant);

            Optional<CarAccidentParticipant> createdCarAccidentParticipant = carAccidentParticipantRepository.findByCarAccidentParticipantAndCarAccidentEntity(user, createdCarAccidentEntity.get());

            if(createdCarAccidentParticipant.isEmpty()) {
                throw new InternalApiException("Не удалось добавить некоторых из пользователей");
            }

            Chat chat = Chat
                    .builder()
                    .carAccidentEntity(createdCarAccidentEntity.get())
                    .carAccidentParticipant(user)
                    .trafficPoliceOfficer(createdCarAccidentEntity.get().getTrafficPoliceOfficer())
                    .build();

            chatRepository.save(chat);
        }

        return new CreationResponse("CarAccident", createdCarAccident.get().getId());
    }

    @Override
    public CreationResponse synchronizeWithExistingCarAccidentEntity(ArrayList<Users> userList, Long entityID) {
        for(Users user: userList) {
            addCarAccidentParticipant(new CarAccidentParticipantAddRequest(entityID, user.getId()));
        }
        return new CreationResponse("CarAccidentEntity", entityID);
    }

    @Override
    public List<CarAccidentEntity> getUsersCarAccidentsInfo(Long userID) {
        ArrayList<CarAccidentEntity> result = new ArrayList<>();
        Optional<Users> userCandidate = usersRepository.findById(userID);
        if(userCandidate.isEmpty()) {
            throw new ApiRequestException("Пользователь не был найден");
        }
        Optional<List<CarAccidentParticipant>> listCandidate = carAccidentParticipantRepository.findAllByCarAccidentParticipant(userCandidate.get());
        if(listCandidate.isPresent()) {
            for(CarAccidentParticipant listEntity: listCandidate.get()) {
                result.add(listEntity.getCarAccidentEntity());
            }
        }
        return result;
    }

    @Override
    public CarAccidentEntity getCarAccidentInfo(CarAccidentInfoRequest carAccidentInfoRequest) {
        Long entityID = carAccidentInfoRequest.getCarAccidentEntityID();
        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(entityID);
        if(carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }
        return carAccidentEntityCandidate.get();
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


    public StringResponse changeEntityState(Long entityID, String entityStateName) {
        CarAccidentEntity carAccidentEntity = getEntity(entityID);
        Optional<CarAccidentEntityState> carAccidentEntityState = carAccidentEntityStateRepository.findByCarAccidentEntityState(entityStateName);

        if(carAccidentEntityState.isEmpty()) {
            throw new ApiRequestException("Такого состояния ДТП не существует");
        }

        carAccidentEntity.setEntityState(carAccidentEntityState.get());
        carAccidentEntityRepository.save(carAccidentEntity);
        return new StringResponse("Состояние ДТП было изменено");
    }
}
