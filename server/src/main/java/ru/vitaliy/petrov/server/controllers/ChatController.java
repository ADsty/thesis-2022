package ru.vitaliy.petrov.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vitaliy.petrov.server.dto.ChatDTO;
import ru.vitaliy.petrov.server.dto.MessageDTO;
import ru.vitaliy.petrov.server.error.ApiRequestException;
import ru.vitaliy.petrov.server.forms.requests.chats.MessageCreationRequest;
import ru.vitaliy.petrov.server.forms.requests.chats.MessageUpdateRequest;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.forms.responses.StringResponse;
import ru.vitaliy.petrov.server.models.Chat;
import ru.vitaliy.petrov.server.models.Message;
import ru.vitaliy.petrov.server.models.MessageContent;
import ru.vitaliy.petrov.server.security.JwtUtil;
import ru.vitaliy.petrov.server.services.ChatService;
import ru.vitaliy.petrov.server.services.UserProfileService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ChatController {


    @Autowired
    private ChatService chatService;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/chats/get-all-user")
    public List<ChatDTO> getAllUsersChats(@RequestHeader("Authorization") String jwtToken) {
        jwtUtil.checkTokenUserRole(jwtToken, "USER");
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        ArrayList<ChatDTO> result = new ArrayList<>();
        List<Chat> chats = chatService.getAllChatsOfUser(userID);
        for (Chat chat : chats) {
            Message lastMessageCandidate = chatService.getLastMessageOfChat(chat);
            if (lastMessageCandidate == null) {
                result.add(new ChatDTO(chat, null));
            } else {
                result.add(new ChatDTO(chat, getMessageDTOContent(lastMessageCandidate)));
            }
        }
        return result;
    }

    @GetMapping("/chats/get-all-officer")
    public List<ChatDTO> getAllPoliceOfficerChats(@RequestHeader("Authorization") String jwtToken) {
        jwtUtil.checkTokenUserRole(jwtToken, "TRAFFIC OFFICER");
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        ArrayList<ChatDTO> result = new ArrayList<>();
        List<Chat> chats = chatService.getAllChatsOfPoliceOfficer(userID);
        for (Chat chat : chats) {
            Message lastMessageCandidate = chatService.getLastMessageOfChat(chat);
            if (lastMessageCandidate == null) {
                result.add(new ChatDTO(chat, null));
            } else {
                result.add(new ChatDTO(chat, getMessageDTOContent(lastMessageCandidate)));
            }
        }
        return result;
    }

    @GetMapping("/chats/messages/get-all")
    public List<MessageDTO> getAllChatMessages(@RequestHeader("Authorization") String jwtToken, Long chatID) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        ArrayList<MessageDTO> result = new ArrayList<>();
        List<Message> messages = chatService.getAllMessagesOfChat(chatID, userID);

        for (Message message : messages) {
            result.add(getMessageDTOContent(message));
        }

        return result;
    }

    @PostMapping("/chats/messages/send")
    public CreationResponse sendMessage(@Valid MessageCreationRequest messageCreationRequest, BindingResult bindingResult, @RequestHeader("Authorization") String jwtToken) {
        if (bindingResult.hasErrors())
            throw new ApiRequestException("Введенные данные неверны");
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return chatService.sendMessage(messageCreationRequest, userID);
    }

    @PatchMapping("/chats/messages/update")
    public StringResponse updateMessage(@RequestHeader("Authorization") String jwtToken, @Valid MessageUpdateRequest messageUpdateRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ApiRequestException("Введенные данные неверны");
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return new StringResponse(chatService.updateMessage(messageUpdateRequest, userID));
    }

    private MessageDTO getMessageDTOContent(Message message) {
        MessageContent messageContent = chatService.getMessageContent(message);
        String senderFullName = userProfileService.getUserProfile(message.getSender().getId()).getUserFullName();
        String addresseeFullName = userProfileService.getUserProfile(message.getAddressee().getId()).getUserFullName();
        return new MessageDTO(message, messageContent, senderFullName, addresseeFullName);
    }

}
