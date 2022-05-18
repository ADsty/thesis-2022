package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class VehicleProfileDeleteRequest {
    private final String vehicleVIN;
}
