package ru.vitaliy.petrov.server.forms.requests.documents;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

@Data
public class AdministrativeOffenceCaseRefusalUpdateRequest {

    @NotNull
    private final Long documentID;

    @NotNull
    private final Date updatedDateOfFill;

    @NotNull
    private final Time updatedTimeOfFill;

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
    private final Long carAccidentEntityID;
}
