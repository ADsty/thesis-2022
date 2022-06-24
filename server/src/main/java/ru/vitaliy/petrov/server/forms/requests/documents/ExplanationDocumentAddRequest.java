package ru.vitaliy.petrov.server.forms.requests.documents;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

@Data
public class ExplanationDocumentAddRequest {

    @NotNull
    private final Date dateOfFill;

    @NotNull
    private final Time timeOfFill;

    @NotNull
    @NotEmpty
    private final String placeOfFill;

    @NotNull
    private final Long policeOfficerID;

    private final Long carAccidentParticipant;

    private final Long carAccidentWitness;

    @NotNull
    @NotEmpty
    private final String interviewedPersonType;

    @NotNull
    private final Long carAccidentEntityID;

}
