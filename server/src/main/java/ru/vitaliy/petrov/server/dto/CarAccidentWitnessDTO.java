package ru.vitaliy.petrov.server.dto;

import lombok.Data;
import ru.vitaliy.petrov.server.models.CarAccidentWitness;

@Data
public class CarAccidentWitnessDTO {

    private Long entityID;
    private String witnessFullName;
    private String witnessResidentialAddress;
    private String witnessPhoneNumber;

    public CarAccidentWitnessDTO(CarAccidentWitness carAccidentWitness) {
        this.entityID = carAccidentWitness.getCarAccidentEntity().getId();
        this.witnessFullName = carAccidentWitness.getWitnessFullName();
        this.witnessResidentialAddress = carAccidentWitness.getWitnessResidentialAddress();
        this.witnessPhoneNumber = carAccidentWitness.getWitnessPhoneNumber();
    }
}
