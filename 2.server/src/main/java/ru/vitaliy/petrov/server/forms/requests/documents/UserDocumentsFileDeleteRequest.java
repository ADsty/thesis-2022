package ru.vitaliy.petrov.server.forms.requests.documents;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserDocumentsFileDeleteRequest {

    @NotNull
    @NotEmpty
    private String fileLink;
}
