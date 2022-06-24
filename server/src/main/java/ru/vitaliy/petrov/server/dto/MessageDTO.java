package ru.vitaliy.petrov.server.dto;

import lombok.Data;
import ru.vitaliy.petrov.server.models.Message;
import ru.vitaliy.petrov.server.models.MessageContent;

import java.sql.Date;
import java.sql.Time;

@Data
public class MessageDTO {
    private Long messageID;
    private Long chatID;
    private String senderFullName;
    private Long senderID;
    private String addresseeFullName;
    private Long addresseeID;
    private Date messageCreationDate;
    private Time messageCreationTime;
    private Date messageUpdateDate;
    private Time messageUpdateTime;
    private String messageText;


    public MessageDTO(Message message, MessageContent messageContent, String senderFullName, String addresseeFullName) {
        this.messageID = message.getId();
        this.chatID = message.getChat().getId();
        this.senderFullName = senderFullName;
        this.senderID = message.getSender().getId();
        this.addresseeFullName = addresseeFullName;
        this.addresseeID = message.getAddressee().getId();
        this.messageCreationDate = message.getMessageCreationDate();
        this.messageCreationTime = message.getMessageCreationTime();
        this.messageUpdateDate = message.getMessageUpdateDate();
        this.messageUpdateTime = message.getMessageUpdateTime();
        this.messageText = messageContent.getMessageText();
    }

}
