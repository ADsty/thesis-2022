package ru.vitaliy.petrov.server.forms.requests.chats;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MessageUpdateRequest {

    @NotNull
    @NotEmpty
    private final String messageUpdateDate;

    @NotNull
    @NotEmpty
    private final String messageUpdateTime;

    @NotNull
    @NotEmpty
    private final String updatedMessageText;

    @NotNull
    @NotEmpty
    private final String fileName;

    @NotNull
    @NotEmpty
    private final Long messageID;
}
