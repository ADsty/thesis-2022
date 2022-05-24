package ru.vitaliy.petrov.server.forms.requests.documents;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AdministrativeOffenceCaseRefusalUpdateRequest {

    @NotNull
    @NotEmpty
    private final String updatedDateOfFill;

    @NotNull
    @NotEmpty
    private final String updatedTimeOfFill;

    @NotNull
    @NotEmpty
    private final String updatedPlaceOfFill;

    @NotNull
    @NotEmpty
    private final String updatedCarAccidentEstablishedInfo;

    @NotNull
    @NotEmpty
    private final String updatedRefusalReason;

    @NotNull
    @NotEmpty
    private final String updatedRefusalLawInfo;

    @NotNull
    @NotEmpty
    private final Long entityDocumentsID;
}
