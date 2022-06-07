package ru.vitaliy.petrov.server.forms.requests.documents;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;

@Data
public class AdministrativeOffenceCaseInvestigationAddRequest {

    @NotNull
    private Date dateOfFill;

    @NotNull
    private Time timeOfFill;

    @NotNull
    @NotEmpty
    private String placeOfFill;

    @NotNull
    private Long policeOfficerID;

    @NotNull
    private Long carAccidentEntityID;

    @NotNull
    @NotEmpty
    private String investigationReason;

    @NotNull
    @NotEmpty
    private String lawViolationInfo;

}
