package ru.vitaliy.petrov.server.forms.requests.chats;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

@Data
public class MessageCreationRequest {

    @NotNull
    private final Long addressee;

    @NotNull
    private final Date messageCreationDate;

    @NotNull
    private final Time messageCreationTime;

    @NotNull
    @NotEmpty
    private final String messageText;

    @NotNull
    private final Long chatID;
}
