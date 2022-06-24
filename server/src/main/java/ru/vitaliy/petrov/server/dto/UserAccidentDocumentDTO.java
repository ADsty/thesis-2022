package ru.vitaliy.petrov.server.dto;

import lombok.Data;
import ru.vitaliy.petrov.server.models.UserAccidentDocument;

import java.sql.Date;
import java.sql.Time;

@Data
public class UserAccidentDocumentDTO {

    private Long userAccidentDocumentID;
    private Long carAccidentEntityID;
    private Date sendDate;
    private Time sendTime;
    private Long senderParticipantID;
    private String explanationText;

    public UserAccidentDocumentDTO(UserAccidentDocument userAccidentDocument) {
        this.userAccidentDocumentID = userAccidentDocument.getId();
        this.carAccidentEntityID = userAccidentDocument.getCarAccidentEntityDocuments().getCarAccidentEntity().getId();
        this.sendDate = userAccidentDocument.getSendDate();
        this.sendTime = userAccidentDocument.getSendTime();
        this.senderParticipantID = userAccidentDocument.getSenderParticipant().getId();
        this.explanationText = userAccidentDocument.getExplanationText();
    }

}
