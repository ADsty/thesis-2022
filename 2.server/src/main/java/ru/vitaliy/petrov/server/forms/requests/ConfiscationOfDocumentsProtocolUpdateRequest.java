package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class ConfiscationOfDocumentsProtocolUpdateRequest {
    private final String updatedDateOfFill;
    private final String updatedTimeOfFill;
    private final String updatedPlaceOfFill;
    private final Long updatedCarAccidentParticipant;
    private final String updatedConfiscationReason;
    private final String updatedConfiscatedDocumentsInfo;
    private final String updatedConfiscationProcessFixationMethod;
    private final String updatedFirstWitnessFullName;
    private final String updatedFirstWitnessResidentialAddress;
    private final String updatedFirstWitnessPhoneNumber;
    private final String updatedSecondWitnessFullName;
    private final String updatedSecondWitnessResidentialAddress;
    private final String updatedSecondWitnessPhoneNumber;
    private final Long entityDocumentsID;
}
