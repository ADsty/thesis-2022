package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class CarAccidentParticipantDeleteRequest {
    private final Long participantID;
}
