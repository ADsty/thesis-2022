package ru.vitaliy.petrov.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vitaliy.petrov.server.error.ApiRequestException;
import ru.vitaliy.petrov.server.forms.requests.chats.MessageCreationRequest;
import ru.vitaliy.petrov.server.forms.requests.chats.MessageUpdateRequest;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.forms.responses.StringResponse;
import ru.vitaliy.petrov.server.models.Chat;
import ru.vitaliy.petrov.server.models.Message;
import ru.vitaliy.petrov.server.security.JwtUtil;
import ru.vitaliy.petrov.server.services.ChatService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ChatController {


    @Autowired
    private ChatService chatService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/chats/get-all")
    public List<Chat> getAllUsersChats(@RequestHeader("Authorization") String jwtToken) {
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return chatService.getAllChatsOfUser(userID);
    }

    @GetMapping("/chats/messages/get-all")
    public List<Message> getAllChatMessages(Long chatID) {
        return chatService.getAllMessagesOfChat(chatID);
    }

    @PostMapping("/chats/messages/send")
    public CreationResponse sendMessage(@Valid MessageCreationRequest messageCreationRequest, BindingResult bindingResult, @RequestHeader("Authorization") String jwtToken) {
        if(bindingResult.hasErrors())
            throw new ApiRequestException("Введенные данные неверны");
        Long userID = jwtUtil.extractAllClaimsFromHeader(jwtToken).get("id", Long.class);
        return chatService.sendMessage(messageCreationRequest, userID);
    }

    @PatchMapping("/chats/messages/update")
    public StringResponse updateMessage(@Valid MessageUpdateRequest messageUpdateRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            throw new ApiRequestException("Введенные данные неверны");
        return new StringResponse(chatService.updateMessage(messageUpdateRequest));
    }

}
