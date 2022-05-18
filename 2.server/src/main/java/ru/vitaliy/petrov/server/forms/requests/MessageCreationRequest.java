package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class MessageCreationRequest {
    private final Long addressee;
    private final String messageCreationDate;
    private final String messageCreationTime;
    private final String messageText;
    private final Long chatID;
    private final Boolean isFileLinked;
}
