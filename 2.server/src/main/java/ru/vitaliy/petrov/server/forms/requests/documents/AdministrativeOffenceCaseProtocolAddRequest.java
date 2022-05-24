package ru.vitaliy.petrov.server.forms.requests.documents;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AdministrativeOffenceCaseProtocolAddRequest {

    @NotNull
    @NotEmpty
    private final String dateOfFill;

    @NotNull
    @NotEmpty
    private final String timeOfFill;

    @NotNull
    @NotEmpty
    private final String placeOfFill;

    @NotNull
    @NotEmpty
    private final Long policeOfficerID;

    @NotNull
    @NotEmpty
    private final Long culpritID;

    @NotNull
    @NotEmpty
    private final String culpritActualPlaceOfResidence;

    @NotNull
    @NotEmpty
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

    @NotNull
    @NotEmpty
    private final Long entityDocumentsID;
}
