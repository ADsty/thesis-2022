package ru.vitaliy.petrov.server.forms.requests.documents;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

@Data
public class AdministrativeOffenceCaseDecisionAddRequest {

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
    private final String caseCircumstances;

    @NotNull
    @NotEmpty
    private final String caseDecision;

    @NotNull
    private final Date dateOfReceiving;

    @NotNull
    private final Date effectiveDate;

}
