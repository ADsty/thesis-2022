package ru.vitaliy.petrov.server.forms.requests.documents;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

@Data
public class ConfiscationOfDocumentsProtocolUpdateRequest {

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
    private final Long updatedCarAccidentParticipant;

    @NotNull
    @NotEmpty
    private final String updatedConfiscationReason;

    @NotNull
    @NotEmpty
    private final String updatedConfiscatedDocumentsInfo;

    @NotNull
    @NotEmpty
    private final String updatedConfiscationProcessFixationMethod;

    @NotNull
    private final Long updatedFirstWitnessID;

    @NotNull
    private final Long updatedSecondWitnessID;

    @NotNull
    private final Long carAccidentEntityID;
}
