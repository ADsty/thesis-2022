package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class MessageUpdateRequest {
    private final String messageUpdateDate;
    private final String messageUpdateTime;
    private final String updatedMessageText;
    private final Boolean isFileLinked;
    private final Long messageID;
}
