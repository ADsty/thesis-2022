package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class CarAccidentWitnessAddRequest {
    private final Long entityID;
    private final String witnessFullName;
    private final String witnessResidentialAddress;
    private final String witnessPhoneNumber;
}
