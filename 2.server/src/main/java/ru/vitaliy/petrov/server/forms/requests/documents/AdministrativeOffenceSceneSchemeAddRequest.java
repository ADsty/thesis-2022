package ru.vitaliy.petrov.server.forms.requests.documents;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

@Data
public class AdministrativeOffenceSceneSchemeAddRequest {

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
    private final String fileLink;

    @NotNull
    @NotEmpty
    private final String schemeConventions;

    @NotNull
    private final Long firstWitnessID;

    @NotNull
    private final Long secondWitnessID;

    @NotNull
    private final Long carAccidentEntityID;
}
