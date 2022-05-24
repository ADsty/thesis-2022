package ru.vitaliy.petrov.server.forms.requests.chats;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MessageCreationRequest {

    @NotNull
    @NotEmpty
    private final Long addressee;

    @NotNull
    @NotEmpty
    private final String messageCreationDate;

    @NotNull
    @NotEmpty
    private final String messageCreationTime;

    @NotNull
    @NotEmpty
    private final String messageText;

    @NotNull
    @NotEmpty
    private final Long chatID;

    @NotNull
    @NotEmpty
    private final String fileName;
}
