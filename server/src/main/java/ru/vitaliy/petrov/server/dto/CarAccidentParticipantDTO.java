package ru.vitaliy.petrov.server.dto;

import lombok.Data;
import ru.vitaliy.petrov.server.models.CarAccidentParticipant;
import ru.vitaliy.petrov.server.models.UserProfile;

@Data
public class CarAccidentParticipantDTO {

    private Long entityID;
    private UserProfileDTO userProfileDTO;
    private VehicleProfileDTO vehicleProfileDTO;

    public CarAccidentParticipantDTO(CarAccidentParticipant carAccidentParticipant, UserProfile userProfile) {
        this.entityID = carAccidentParticipant.getCarAccidentEntity().getId();
        this.userProfileDTO = new UserProfileDTO(userProfile);
        this.vehicleProfileDTO = new VehicleProfileDTO(carAccidentParticipant.getParticipantVehicle());
    }

}
