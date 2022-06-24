package ru.vitaliy.petrov.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vitaliy.petrov.server.error.ApiRequestException;
import ru.vitaliy.petrov.server.error.ForbiddenApiException;
import ru.vitaliy.petrov.server.error.InternalApiException;
import ru.vitaliy.petrov.server.forms.requests.caraccident.*;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.forms.responses.StringResponse;
import ru.vitaliy.petrov.server.models.*;
import ru.vitaliy.petrov.server.repositories.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
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

    @Autowired
    private UsersService usersService;

    @Autowired
    private ChangelogService changelogService;

    @Autowired
    private CarAccidentEntityDocumentsRepository carAccidentEntityDocumentsRepository;

    @Autowired
    private ChangeLabelRepository changeLabelRepository;

    @Autowired
    private CarAccidentEntityChangelogRepository carAccidentEntityChangelogRepository;

    @Autowired
    private VehicleProfileRepository vehicleProfileRepository;

    @Override
    public CarAccident getCarAccident(Long carAccidentID) {
        return getAccident(carAccidentID);
    }

    @Override
    public String updateCarAccident(CarAccidentUpdateRequest carAccidentUpdateRequest, Long carAccidentID) {
        CarAccident carAccident = getCarAccident(carAccidentID);

        final String updatedCarAccidentScene = carAccidentUpdateRequest.getUpdatedCarAccidentScene();
        final Date updatedCarAccidentDate = carAccidentUpdateRequest.getUpdatedCarAccidentDate();
        final Time updatedCarAccidentTime = carAccidentUpdateRequest.getUpdatedCarAccidentTime();

        Optional<CarAccidentEntity> carAccidentEntity = carAccidentEntityRepository.findByCarAccident(carAccident);

        if (carAccidentEntity.isEmpty()) {
            throw new ApiRequestException("Сущность ДТП не найдена");
        }

        if (isCarAccidentClosed(carAccidentEntity.get())) {
            throw new ForbiddenApiException("Данное ДТП закрыто для изменений");
        }

        carAccident.setCarAccidentScene(updatedCarAccidentScene);
        carAccident.setCarAccidentDate(updatedCarAccidentDate);
        carAccident.setCarAccidentTime(updatedCarAccidentTime);


        carAccidentRepository.save(carAccident);


        changelogService.addNewRecord(carAccidentEntity.get().getId(), "ДТП №" + carAccidentEntity.get().getId() + " было обновлено", "Служебный", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

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
        final Long vehicleID = carAccidentParticipantAddRequest.getVehicleID();

        final Optional<Users> participant = usersRepository.findById(participantID);
        final CarAccidentEntity carAccidentEntity = getEntity(entityID);
        final Optional<VehicleProfile> vehicleProfileCandidate = vehicleProfileRepository.findById(vehicleID);

        if (isCarAccidentClosed(carAccidentEntity)) {
            throw new ForbiddenApiException("Данное ДТП закрыто для изменений");
        }

        if (participant.isEmpty()) {
            throw new ApiRequestException("Неверные данные пользователя");
        }

        if (vehicleProfileCandidate.isEmpty()) {
            throw new ApiRequestException("Машина пользователя не найдена");
        }


        CarAccidentParticipant carAccidentParticipant = CarAccidentParticipant
                .builder()
                .carAccidentEntity(carAccidentEntity)
                .carAccidentParticipant(participant.get())
                .participantVehicle(vehicleProfileCandidate.get())
                .build();

        carAccidentParticipantRepository.save(carAccidentParticipant);
        Optional<CarAccidentParticipant> createdParticipant = carAccidentParticipantRepository.findByCarAccidentParticipantAndCarAccidentEntity(participant.get(), carAccidentEntity);

        if (createdParticipant.isEmpty()) {
            throw new InternalApiException("Не удалось добавить участника ДТП");
        }

        carAccidentEntity.getCarAccident().setCarAccidentParticipantsNumber(carAccidentEntity.getCarAccident().getCarAccidentParticipantsNumber() + 1);

        changelogService.addNewRecord(entityID, "Участник №" + participantID + " был добавлен в ДТП №" + carAccidentEntity.getId(), "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        if (carAccidentEntity.getTrafficPoliceOfficer() != null) {
            Chat chat = Chat
                    .builder()
                    .carAccidentEntity(carAccidentEntity)
                    .carAccidentParticipant(participant.get())
                    .trafficPoliceOfficer(carAccidentEntity.getTrafficPoliceOfficer())
                    .build();

            chatRepository.save(chat);

            changelogService.addNewRecord(entityID, "Чат с участником " + participantID + " из ДТП №" + carAccidentEntity.getId() + " был добавлен", "Служебный", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        }
        return new CreationResponse("CarAccidentParticipant", createdParticipant.get().getId());
    }

    @Override
    public CreationResponse addCarAccidentWitness(CarAccidentWitnessAddRequest carAccidentWitnessAddRequest) {
        final Long entityID = carAccidentWitnessAddRequest.getEntityID();
        final String witnessFullName = carAccidentWitnessAddRequest.getWitnessFullName();
        final String witnessResidentialAddress = carAccidentWitnessAddRequest.getWitnessResidentialAddress();
        final String witnessPhoneNumber = carAccidentWitnessAddRequest.getWitnessPhoneNumber();

        final CarAccidentEntity carAccidentEntity = getEntity(entityID);

        if (isCarAccidentClosed(carAccidentEntity)) {
            throw new ForbiddenApiException("Данное ДТП закрыто для изменений");
        }


        CarAccidentWitness carAccidentWitness = CarAccidentWitness
                .builder()
                .carAccidentEntity(carAccidentEntity)
                .witnessFullName(witnessFullName)
                .witnessResidentialAddress(witnessResidentialAddress)
                .witnessPhoneNumber(witnessPhoneNumber)
                .build();

        carAccidentWitnessRepository.save(carAccidentWitness);

        Optional<CarAccidentWitness> createdWitness = carAccidentWitnessRepository.findByCarAccidentEntityAndWitnessFullName(carAccidentEntity, witnessFullName);

        if (createdWitness.isEmpty()) {
            throw new InternalApiException("Не удалось добавить свидетеля ДТП");
        }

        carAccidentEntity.getCarAccident().setCarAccidentWitnessesNumber(carAccidentEntity.getCarAccident().getCarAccidentWitnessesNumber() + 1);

        changelogService.addNewRecord(entityID, "Свидетель №" + createdWitness.get().getId() + " был добавлен в ДТП №" + entityID, "Служебный", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        return new CreationResponse("CarAccidentWitness", createdWitness.get().getId());
    }

    @Override
    public List<CarAccidentParticipant> getCarAccidentParticipants(Long entityID) {
        Optional<CarAccidentEntity> entityCandidate = carAccidentEntityRepository.findById(entityID);
        if (entityCandidate.isEmpty()) {
            throw new ApiRequestException("Не удалось найти сущность ДТП");
        }
        Optional<List<CarAccidentParticipant>> carAccidentParticipantListCandidate = carAccidentParticipantRepository.findAllByCarAccidentEntity(entityCandidate.get());

        if (carAccidentParticipantListCandidate.isEmpty()) {
            return new ArrayList<>();
        }
        return carAccidentParticipantListCandidate.get();
    }

    @Override
    public List<CarAccidentWitness> getCarAccidentWitnesses(Long entityID) {
        Optional<CarAccidentEntity> entityCandidate = carAccidentEntityRepository.findById(entityID);
        if (entityCandidate.isEmpty()) {
            throw new ApiRequestException("Не удалось найти сущность ДТП");
        }
        Optional<List<CarAccidentWitness>> witnessesCandidate = carAccidentWitnessRepository.findAllByCarAccidentEntity(entityCandidate.get());

        if (witnessesCandidate.isEmpty()) {
            return new ArrayList<>();
        }
        return witnessesCandidate.get();
    }

    @Override
    public String deleteCarAccidentParticipant(CarAccidentParticipantDeleteRequest carAccidentParticipantDeleteRequest) {
        final Long participantID = carAccidentParticipantDeleteRequest.getParticipantID();
        final CarAccidentParticipant carAccidentParticipant = getParticipant(participantID);
        if (isCarAccidentClosed(carAccidentParticipant.getCarAccidentEntity())) {
            throw new ForbiddenApiException("Данное ДТП закрыто для изменений");
        }

        carAccidentParticipantRepository.delete(carAccidentParticipant);

        changelogService.addNewRecord(carAccidentParticipant.getCarAccidentEntity().getId(), "Участник №" + carAccidentParticipant.getId() + " был удален из ДТП №" + carAccidentParticipant.getCarAccidentEntity().getId(), "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        return "Участник ДТП удален";
    }

    @Override
    public String deleteCarAccidentWitness(CarAccidentWitnessDeleteRequest carAccidentWitnessDeleteRequest) {
        final Long witnessID = carAccidentWitnessDeleteRequest.getWitnessID();
        final CarAccidentWitness carAccidentWitness = getWitness(witnessID);
        if (isCarAccidentClosed(carAccidentWitness.getCarAccidentEntity())) {
            throw new ForbiddenApiException("Данное ДТП закрыто для изменений");
        }

        carAccidentWitnessRepository.delete(carAccidentWitness);

        changelogService.addNewRecord(carAccidentWitness.getCarAccidentEntity().getId(), "Свидетель №" + carAccidentWitness.getId() + " был удален из ДТП №" + carAccidentWitness.getCarAccidentEntity().getId(), "Служебный", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        return "Свидетель ДТП удален";
    }

    @Override
    public String updateCarAccidentWitness(CarAccidentWitnessUpdateRequest carAccidentWitnessUpdateRequest) {
        final Long witnessID = carAccidentWitnessUpdateRequest.getWitnessID();
        final String updatedWitnessFullName = carAccidentWitnessUpdateRequest.getUpdatedWitnessFullName();
        final String updatedWitnessResidentialAddress = carAccidentWitnessUpdateRequest.getUpdatedWitnessResidentialAddress();
        final String updatedWitnessPhoneNumber = carAccidentWitnessUpdateRequest.getUpdatedWitnessPhoneNumber();

        final CarAccidentWitness witness = getWitness(witnessID);

        if (isCarAccidentClosed(witness.getCarAccidentEntity())) {
            throw new ForbiddenApiException("Данное ДТП закрыто для изменений");
        }

        witness.setWitnessFullName(updatedWitnessFullName);
        witness.setWitnessResidentialAddress(updatedWitnessResidentialAddress);
        witness.setWitnessPhoneNumber(updatedWitnessPhoneNumber);

        carAccidentWitnessRepository.save(witness);

        changelogService.addNewRecord(witness.getCarAccidentEntity().getId(), "Свидетель №" + witness.getId() + " из ДТП №" + witness.getCarAccidentEntity().getId() + " был обновлен", "Служебный", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        return "Свидетель ДТП был обновлен";
    }

    private CarAccident getAccident(Long accidentID) {
        Optional<CarAccident> carAccidentCandidate = carAccidentRepository.findById(accidentID);

        if (carAccidentCandidate.isEmpty()) {
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
        List<Long> participantsVehicles = carAccidentCreationRequest.getCarAccidentParticipantsVehicles();
        for (Long participantID : participantsList) {
            Optional<Users> userCandidate = usersRepository.findById(participantID);
            if (userCandidate.isEmpty()) {
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
        if (isAlreadyCreated && createdEntityID != null) {
            return synchronizeWithExistingCarAccidentEntity(userList, participantsVehicles, createdEntityID);
        }

        final String carAccidentScene = carAccidentCreationRequest.getCarAccidentScene();
        final Date carAccidentDate = carAccidentCreationRequest.getCarAccidentDate();
        final Time carAccidentTime = carAccidentCreationRequest.getCarAccidentTime();
        final int carAccidentParticipantsNumber = participantsList.size();
        final int carAccidentWitnessesNumber = 0;

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
        if (createdCarAccident.isEmpty()) {
            throw new InternalApiException("Не удалось создать сущность ДТП");
        }

        Optional<CarAccidentEntityState> carAccidentEntityState = carAccidentEntityStateRepository.findByCarAccidentEntityState("Необработанное заявление");

        if (carAccidentEntityState.isEmpty()) {
            throw new InternalApiException("Нарушена целостность данных");
        }

        CarAccidentEntity carAccidentEntity = CarAccidentEntity
                .builder()
                .carAccident(createdCarAccident.get())
                .entityState(carAccidentEntityState.get())
                .trafficPoliceOfficer(null)
                .build();

        carAccidentEntityRepository.save(carAccidentEntity);

        Optional<CarAccidentEntity> createdCarAccidentEntity = carAccidentEntityRepository.findByCarAccident(createdCarAccident.get());

        if (createdCarAccidentEntity.isEmpty()) {
            throw new InternalApiException("Не удалось создать сущность ДТП");
        }

        for (Users user : userList) {
            addCarAccidentParticipant(new CarAccidentParticipantAddRequest(createdCarAccidentEntity.get().getId(), user.getId(), participantsVehicles.get(userList.indexOf(user))));
        }

        Optional<UserState> readyForWorkState = userStateRepository.findByUserStateName("Готов к работе");

        if (readyForWorkState.isEmpty()) {
            throw new InternalApiException("Нарушена целостность базы данных");
        }

        Optional<Users> trafficPoliceOfficer = usersRepository.findFirstByUserState(readyForWorkState.get());

        if (trafficPoliceOfficer.isPresent()) {

            usersService.changeUserState(trafficPoliceOfficer.get().getId(), "В работе");

            carAccidentEntity.setTrafficPoliceOfficer(trafficPoliceOfficer.get());

            Optional<CarAccidentEntityState> updatedCarAccidentEntityState = carAccidentEntityStateRepository.findByCarAccidentEntityState("Обработанное заявление");

            if (updatedCarAccidentEntityState.isEmpty()) {
                throw new InternalApiException("Нарушена целостность базы данных");
            }

            carAccidentEntity.setEntityState(updatedCarAccidentEntityState.get());

            carAccidentEntityRepository.save(carAccidentEntity);

            changelogService.addNewRecord(carAccidentEntity.getId(), "Состояние дела о ДТП №" + carAccidentEntity.getId() + " было изменено: текущее состояние - Обработанное заявление", "Служебный", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));


            Optional<List<CarAccidentParticipant>> carAccidentParticipantList = carAccidentParticipantRepository.findAllByCarAccidentEntity(carAccidentEntity);

            if (carAccidentParticipantList.isEmpty()) {
                throw new ApiRequestException("Не удалось найти участников ДТП");
            }

            for (CarAccidentParticipant user : carAccidentParticipantList.get()) {
                Chat chat = Chat
                        .builder()
                        .carAccidentEntity(carAccidentEntity)
                        .carAccidentParticipant(user.getCarAccidentParticipant())
                        .trafficPoliceOfficer(carAccidentEntity.getTrafficPoliceOfficer())
                        .build();

                chatRepository.save(chat);

                changelogService.addNewRecord(carAccidentEntity.getId(), "Чат с участником " + user.getCarAccidentParticipant().getId() + " из ДТП №" + carAccidentEntity.getId() + " был добавлен", "Служебный", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

            }

        }

        CarAccidentEntityDocuments carAccidentEntityDocuments = CarAccidentEntityDocuments
                .builder()
                .carAccidentEntity(createdCarAccidentEntity.get())
                .build();

        carAccidentEntityDocumentsRepository.save(carAccidentEntityDocuments);

        changelogService.addNewRecord(createdCarAccidentEntity.get().getId(), "ДТП №" + createdCarAccidentEntity.get().getId() + " было создано", "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        return new CreationResponse("CarAccident", createdCarAccident.get().getId());
    }

    @Override
    public CreationResponse synchronizeWithExistingCarAccidentEntity(ArrayList<Users> userList, List<Long> vehicles, Long entityID) {
        if (isCarAccidentClosedForChanges(entityID)) {
            throw new ForbiddenApiException("Данное ДТП не подлежит изменениям");
        }
        for (Users user : userList) {
            addCarAccidentParticipant(new CarAccidentParticipantAddRequest(entityID, user.getId(), vehicles.get(userList.indexOf(user))));
        }
        return new CreationResponse("CarAccidentEntity", entityID);
    }

    @Override
    public List<CarAccidentEntity> getUsersCarAccidentsInfo(Long userID) {
        ArrayList<CarAccidentEntity> result = new ArrayList<>();
        Optional<Users> userCandidate = usersRepository.findById(userID);
        if (userCandidate.isEmpty()) {
            throw new ApiRequestException("Пользователь не был найден");
        }
        Optional<List<CarAccidentParticipant>> listCandidate = carAccidentParticipantRepository.findAllByCarAccidentParticipant(userCandidate.get());
        if (listCandidate.isPresent()) {
            for (CarAccidentParticipant listEntity : listCandidate.get()) {
                result.add(listEntity.getCarAccidentEntity());
            }
        }
        return result;
    }

    public List<CarAccidentEntity> getOfficersCarAccidentsInfo(Long officerID) {
        Optional<Users> officerCandidate = usersRepository.findById(officerID);
        if (officerCandidate.isEmpty()) {
            throw new ApiRequestException("Пользователь не был найден");
        }
        Optional<List<CarAccidentEntity>> listCandidate = carAccidentEntityRepository.findAllByTrafficPoliceOfficer(officerCandidate.get());
        if (listCandidate.isEmpty()) {
            return new ArrayList<>();
        }
        return listCandidate.get();
    }

    @Override
    public CarAccidentEntity getCarAccidentInfo(CarAccidentInfoRequest carAccidentInfoRequest) {
        Long entityID = carAccidentInfoRequest.getCarAccidentEntityID();
        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(entityID);
        if (carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }
        return carAccidentEntityCandidate.get();
    }

    private CarAccidentEntity getEntity(Long entityID) {
        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(entityID);

        if (carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("ДТП не найдено");
        }

        return carAccidentEntityCandidate.get();
    }

    private CarAccidentParticipant getParticipant(Long participantID) {

        Optional<CarAccidentParticipant> carAccidentParticipantCandidate = carAccidentParticipantRepository.findById(participantID);

        if (carAccidentParticipantCandidate.isEmpty()) {
            throw new ApiRequestException("Участник ДТП не найден");
        }
        return carAccidentParticipantCandidate.get();
    }

    private CarAccidentWitness getWitness(Long witnessID) {
        Optional<CarAccidentWitness> carAccidentWitnessCandidate = carAccidentWitnessRepository.findById(witnessID);

        if (carAccidentWitnessCandidate.isEmpty()) {
            throw new ApiRequestException("Свидетель ДТП не найден");
        }
        return carAccidentWitnessCandidate.get();
    }


    public StringResponse changeEntityState(Long entityID, String entityStateName) {
        CarAccidentEntity carAccidentEntity = getEntity(entityID);
        Optional<CarAccidentEntityState> carAccidentEntityState = carAccidentEntityStateRepository.findByCarAccidentEntityState(entityStateName);

        if (carAccidentEntityState.isEmpty()) {
            throw new ApiRequestException("Такого состояния ДТП не существует");
        }

        if (carAccidentEntity.getEntityState().getId() < 3 || carAccidentEntity.getEntityState().getId() >= carAccidentEntityState.get().getId()) {
            throw new ForbiddenApiException("Некорректная смена состояния ДТП");
        }

        carAccidentEntity.setEntityState(carAccidentEntityState.get());
        carAccidentEntityRepository.save(carAccidentEntity);

        changelogService.addNewRecord(entityID, "Состояние ДТП №" + entityID + " было изменено, текущее состояние: " + entityStateName, "Общий", Date.valueOf(LocalDate.now().toString()), Time.valueOf(LocalTime.now().toString().substring(0, 7)));

        return new StringResponse("Состояние ДТП было изменено");
    }

    private boolean isCarAccidentClosed(CarAccidentEntity carAccidentEntity) {
        return carAccidentEntity.getEntityState().getCarAccidentEntityState().equals("Закрытое заявление");
    }

    private boolean isCarAccidentClosedForChanges(Long entityID) {
        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(entityID);
        if (carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("Указанное ДТП не найдено");
        }
        CarAccidentEntity carAccidentEntity = carAccidentEntityCandidate.get();
        return carAccidentEntity.getEntityState().getCarAccidentEntityState().equals("Закрытое заявление") || carAccidentEntity.getEntityState().getCarAccidentEntityState().equals("Расследуемое заявление");
    }

    public List<CarAccidentEntityChangelog> getChangelogOfAccident(Long entityID, Date changeDate, String userRole) {

        Optional<CarAccidentEntity> carAccidentEntityCandidate = carAccidentEntityRepository.findById(entityID);

        if (carAccidentEntityCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        Optional<ChangeLabel> changeLabelUser = changeLabelRepository.findByChangeLabel("Общий");
        Optional<ChangeLabel> changeLabelOfficer = changeLabelRepository.findByChangeLabel("Служебный");

        if (changeLabelUser.isEmpty() || changeLabelOfficer.isEmpty()) {
            throw new InternalApiException("Нарушена целостность базы данных");
        }


        Optional<List<CarAccidentEntityChangelog>> carAccidentEntityChangelogs = carAccidentEntityChangelogRepository.findAllByCarAccidentEntityAndChangeDateAndChangeLabel(carAccidentEntityCandidate.get(), changeDate, changeLabelUser.get());

        if (carAccidentEntityChangelogs.isEmpty()) {
            throw new InternalApiException("История данного ДТП удалена");
        }

        List<CarAccidentEntityChangelog> result = carAccidentEntityChangelogs.get();

        if (userRole.equals("TRAFFIC OFFICER")) {
            carAccidentEntityChangelogs = carAccidentEntityChangelogRepository.findAllByCarAccidentEntityAndChangeDateAndChangeLabel(carAccidentEntityCandidate.get(), changeDate, changeLabelOfficer.get());

            if (carAccidentEntityChangelogs.isEmpty()) {
                throw new InternalApiException("История данного ДТП удалена");
            }
            result.addAll(carAccidentEntityChangelogs.get());
        }
        return result;
    }
}
