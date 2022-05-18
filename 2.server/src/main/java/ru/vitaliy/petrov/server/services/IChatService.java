package ru.vitaliy.petrov.server.services;

import ru.vitaliy.petrov.server.forms.requests.MessageCreationRequest;
import ru.vitaliy.petrov.server.forms.requests.MessageUpdateRequest;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.models.Chat;
import ru.vitaliy.petrov.server.models.Message;

import java.util.List;

public interface IChatService {

    List<Chat> getAllChatsOfUser(Long userID);

    List<Message> getAllMessagesOfChat(Long chatID);

    CreationResponse sendMessage(MessageCreationRequest messageCreationRequest, Long userID);

    String updateMessage(MessageUpdateRequest messageUpdateRequest);
}