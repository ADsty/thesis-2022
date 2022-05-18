package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class CarAccidentParticipantAddRequest {
    private final Long entityID;
    private final Long participantID;
}
