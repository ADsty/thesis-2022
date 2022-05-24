package ru.vitaliy.petrov.server.forms.requests.documents;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ConfiscationOfDocumentsProtocolUpdateRequest {

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
    @NotEmpty
    private final String updatedFirstWitnessFullName;

    @NotNull
    @NotEmpty
    private final String updatedFirstWitnessResidentialAddress;

    @NotNull
    @NotEmpty
    private final String updatedFirstWitnessPhoneNumber;

    @NotNull
    @NotEmpty
    private final String updatedSecondWitnessFullName;

    @NotNull
    @NotEmpty
    private final String updatedSecondWitnessResidentialAddress;

    @NotNull
    @NotEmpty
    private final String updatedSecondWitnessPhoneNumber;

    @NotNull
    @NotEmpty
    private final Long entityDocumentsID;
}
