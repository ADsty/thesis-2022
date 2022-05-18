package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class CarAccidentWitnessDeleteRequest {
    private final Long witnessID;
}
