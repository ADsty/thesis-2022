package ru.vitaliy.petrov.server.forms.requests;

import lombok.Data;

@Data
public class ConfiscationOfDocumentsProtocolAddRequest {
    private final String dateOfFill;
    private final String timeOfFill;
    private final String placeOfFill;
    private final Long policeOfficerID;
    private final Long carAccidentParticipant;
    private final String confiscationReason;
    private final String confiscatedDocumentsInfo;
    private final String confiscationProcessFixationMethod;
    private final String firstWitnessFullName;
    private final String firstWitnessResidentialAddress;
    private final String firstWitnessPhoneNumber;
    private final String secondWitnessFullName;
    private final String secondWitnessResidentialAddress;
    private final String secondWitnessPhoneNumber;
    private final Long entityDocumentsID;
}
