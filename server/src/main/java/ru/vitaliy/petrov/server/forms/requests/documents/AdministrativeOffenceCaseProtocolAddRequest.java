package ru.vitaliy.petrov.server.forms.requests.documents;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

@Data
public class AdministrativeOffenceCaseProtocolAddRequest {

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
    private final Long culpritID;

    @NotNull
    @NotEmpty
    private final String culpritActualPlaceOfResidence;

    @NotNull
    private final Long carAccidentEntityID;

    @NotNull
    @NotEmpty
    private final String lawViolationInfo;

    @NotNull
    @NotEmpty
    private final String caseAdditionalInfo;

    @NotNull
    @NotEmpty
    private final String placeAndTimeOfCaseExamination;

    @NotNull
    @NotEmpty
    private final String changedPlaceOfCaseExamination;

    @NotNull
    @NotEmpty
    private final String explanationsAndRemarksOfProtocol;

}
