package ru.vitaliy.petrov.server.dto;

import lombok.Data;
import ru.vitaliy.petrov.server.models.CarAccident;
import ru.vitaliy.petrov.server.models.CarAccidentEntity;

@Data
public class CarAccidentEntityDTO {

    private Long carAccidentEntityID;
    private String entityState;
    private Long carAccidentID;
    private Long trafficPoliceOfficerID;

    public CarAccidentEntityDTO(CarAccidentEntity carAccidentEntity) {
        this.carAccidentEntityID = carAccidentEntity.getId();
        this.entityState = carAccidentEntity.getEntityState().getCarAccidentEntityState();
        this.carAccidentID = carAccidentEntity.getCarAccident().getId();
        this.trafficPoliceOfficerID = carAccidentEntity.getTrafficPoliceOfficer().getId();
    }

}
