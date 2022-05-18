package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class CarAccidentWitnessUpdateRequest {
    private final String updatedWitnessFullName;
    private final String updatedWitnessResidentialAddress;
    private final String updatedWitnessPhoneNumber;
}
