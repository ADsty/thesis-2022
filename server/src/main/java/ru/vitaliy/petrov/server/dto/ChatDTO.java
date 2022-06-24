package ru.vitaliy.petrov.server.dto;

import lombok.Data;
import ru.vitaliy.petrov.server.models.Chat;

@Data
public class ChatDTO {

    private Long chatID;
    private Long carAccidentParticipantID;
    private Long trafficPoliceOfficerID;
    private Long carAccidentEntityID;
    private MessageDTO lastMessage;

    public ChatDTO(Chat chat, MessageDTO lastMessage) {
        this.chatID = chat.getId();
        this.carAccidentParticipantID = chat.getCarAccidentParticipant().getId();
        this.trafficPoliceOfficerID = chat.getTrafficPoliceOfficer().getId();
        this.carAccidentEntityID = chat.getCarAccidentEntity().getId();
        this.lastMessage = lastMessage;
    }

}
