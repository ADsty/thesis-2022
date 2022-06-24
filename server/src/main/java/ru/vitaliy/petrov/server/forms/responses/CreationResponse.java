package ru.vitaliy.petrov.server.forms.responses;

import lombok.Data;

@Data
public class CreationResponse {
    private final Long createdEntityID;
    private final String createdEntityName;

    public CreationResponse(String createdEntityName, Long createdEntityID) {
        this.createdEntityID = createdEntityID;
        this.createdEntityName = createdEntityName;
    }
}
