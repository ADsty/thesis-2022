package ru.vitaliy.petrov.server.forms.requests.documents;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

@Data
public class AdministrativeOffenceCaseRefusalAddRequest {

    @NotNull
    private final Date dateOfFill;

    @NotNull
    private final Time timeOfFill;

    @NotNull
    @NotEmpty
    private final String placeOfFill;

    @NotNull
    private final Long policeOfficerID;

    @NotNull
    @NotEmpty
    private final Long carAccidentInfoSender;

    @NotNull
    @NotEmpty
    private final String carAccidentEstablishedInfo;

    @NotNull
    @NotEmpty
    private final String refusalReason;

    @NotNull
    @NotEmpty
    private final String refusalLawInfo;

    @NotNull
    private final Long carAccidentEntityID;
}
