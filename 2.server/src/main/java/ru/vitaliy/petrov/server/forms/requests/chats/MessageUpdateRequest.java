package ru.vitaliy.petrov.server.forms.requests.chats;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

@Data
public class MessageUpdateRequest {

    @NotNull
    private final Date messageUpdateDate;

    @NotNull
    private final Time messageUpdateTime;

    @NotNull
    @NotEmpty
    private final String updatedMessageText;

    @NotNull
    private final Long messageID;
}
