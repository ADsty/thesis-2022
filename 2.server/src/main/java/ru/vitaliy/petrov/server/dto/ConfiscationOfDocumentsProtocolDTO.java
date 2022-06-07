package ru.vitaliy.petrov.server.dto;

import lombok.Data;
import ru.vitaliy.petrov.server.models.ConfiscationOfDocumentsProtocol;

import java.sql.Date;
import java.sql.Time;

@Data
public class ConfiscationOfDocumentsProtocolDTO {

    private Long confiscationOfDocumentsProtocolID;
    private Long carAccidentEntityID;
    private Date dateOfFill;
    private Time timeOfFill;
    private String placeOfFill;
    private Long trafficPoliceOfficerID;
    private Long carAccidentParticipantID;
    private String confiscationReason;
    private String confiscatedDocumentsInfo;
    private String confiscationProcessFixationMethod;
    private String firstWitnessFullName;
    private String firstWitnessResidentialAddress;
    private String firstWitnessPhoneNumber;
    private String secondWitnessFullName;
    private String secondWitnessResidentialAddress;
    private String secondWitnessPhoneNumber;

    public ConfiscationOfDocumentsProtocolDTO(ConfiscationOfDocumentsProtocol confiscationOfDocumentsProtocol) {
        this.confiscationOfDocumentsProtocolID = confiscationOfDocumentsProtocol.getId();
        this.carAccidentEntityID = confiscationOfDocumentsProtocol.getCarAccidentEntityDocuments().getCarAccidentEntity().getId();
        this.dateOfFill = confiscationOfDocumentsProtocol.getDateOfFill();
        this.timeOfFill = confiscationOfDocumentsProtocol.getTimeOfFill();
        this.placeOfFill = confiscationOfDocumentsProtocol.getPlaceOfFill();
        this.trafficPoliceOfficerID = confiscationOfDocumentsProtocol.getTrafficPoliceOfficer().getId();
        this.carAccidentParticipantID = confiscationOfDocumentsProtocol.getCarAccidentParticipant().getId();
        this.confiscationReason = confiscationOfDocumentsProtocol.getConfiscationReason();
        this.confiscatedDocumentsInfo = confiscationOfDocumentsProtocol.getConfiscatedDocumentsInfo();
        this.confiscationProcessFixationMethod = confiscationOfDocumentsProtocol.getConfiscationProcessFixationMethod();
        this.firstWitnessFullName = confiscationOfDocumentsProtocol.getConfiscationWitnessesInfo().getFirstWitness().getWitnessFullName();
        this.firstWitnessResidentialAddress = confiscationOfDocumentsProtocol.getConfiscationWitnessesInfo().getFirstWitness().getWitnessResidentialAddress();
        this.firstWitnessPhoneNumber = confiscationOfDocumentsProtocol.getConfiscationWitnessesInfo().getFirstWitness().getWitnessPhoneNumber();
        this.secondWitnessFullName = confiscationOfDocumentsProtocol.getConfiscationWitnessesInfo().getSecondWitness().getWitnessFullName();
        this.secondWitnessResidentialAddress = confiscationOfDocumentsProtocol.getConfiscationWitnessesInfo().getSecondWitness().getWitnessResidentialAddress();
        this.secondWitnessPhoneNumber = confiscationOfDocumentsProtocol.getConfiscationWitnessesInfo().getSecondWitness().getWitnessPhoneNumber();
    }

}
