package ru.vitaliy.petrov.server.dto;

import lombok.Data;
import ru.vitaliy.petrov.server.models.ExplanationDocument;

import java.sql.Date;
import java.sql.Time;

@Data
public class ExplanationDocumentDTO {

    private Long explanationDocumentID;
    private Long carAccidentEntityID;
    private Date dateOfFill;
    private Time timeOfFill;
    private String placeOfFill;
    private Long trafficPoliceOfficerID;
    private Long carAccidentParticipantID;
    private Long carAccidentWitnessID;
    private String interviewedPersonType;

    public ExplanationDocumentDTO(ExplanationDocument explanationDocument) {
        this.explanationDocumentID = explanationDocument.getId();
        this.carAccidentEntityID = explanationDocument.getCarAccidentEntityDocuments().getCarAccidentEntity().getId();
        this.dateOfFill = explanationDocument.getDateOfFill();
        this.timeOfFill = explanationDocument.getTimeOfFill();
        this.placeOfFill = explanationDocument.getPlaceOfFill();
        this.trafficPoliceOfficerID = explanationDocument.getTrafficPoliceOfficer().getId();
        this.carAccidentParticipantID = explanationDocument.getCarAccidentParticipant().getId();
        this.carAccidentWitnessID = explanationDocument.getCarAccidentWitness().getId();
        this.interviewedPersonType = explanationDocument.getInterviewedPersonType().getInterviewedPersonType();
    }

}
