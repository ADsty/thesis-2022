package ru.vitaliy.petrov.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vitaliy.petrov.server.error.ApiRequestException;
import ru.vitaliy.petrov.server.error.InternalApiException;
import ru.vitaliy.petrov.server.forms.requests.MessageCreationRequest;
import ru.vitaliy.petrov.server.forms.requests.MessageUpdateRequest;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.models.Chat;
import ru.vitaliy.petrov.server.models.Message;
import ru.vitaliy.petrov.server.models.MessageContent;
import ru.vitaliy.petrov.server.models.Users;
import ru.vitaliy.petrov.server.repositories.ChatRepository;
import ru.vitaliy.petrov.server.repositories.MessageContentRepository;
import ru.vitaliy.petrov.server.repositories.MessageRepository;
import ru.vitaliy.petrov.server.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService implements IChatService{

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageContentRepository messageContentRepository;

    @Override
    public List<Chat> getAllChatsOfUser(Long userID) {
        Optional<Users> userCandidate = usersRepository.findById(userID);
        if(userCandidate.isEmpty()) {
            throw new ApiRequestException("Не удалось найти пользователя");
        }
        Users user = userCandidate.get();
        return chatRepository.findAllByCarAccidentParticipant(user);
    }

    @Override
    public List<Message> getAllMessagesOfChat(Long chatID) {
        Optional<Chat> chatCandidate = chatRepository.findById(chatID);
        if(chatCandidate.isEmpty()){
            throw new ApiRequestException("Не удалось найти требуемый чат");
        }
        return messageRepository.findAllByChat(chatCandidate.get());
    }

    @Override
    public CreationResponse sendMessage(MessageCreationRequest messageCreationRequest, Long userID) {

        Optional<Users> userCandidate = usersRepository.findById(userID);
        if(userCandidate.isEmpty()) {
            throw new ApiRequestException("Не удалось найти пользователя");
        }

        final Users user = userCandidate.get();
        final Long addresseeID = messageCreationRequest.getAddressee();

        Optional<Users> addresseeCandidate = usersRepository.findById(addresseeID);
        if(addresseeCandidate.isEmpty()) {
            throw new ApiRequestException("Не удалось найти пользователя");
        }

        final Users addressee = addresseeCandidate.get();
        final String messageCreationDate = messageCreationRequest.getMessageCreationDate();
        final String messageCreationTime = messageCreationRequest.getMessageCreationTime();
        final String messageText = messageCreationRequest.getMessageText();
        final Long chatID = messageCreationRequest.getChatID();

        Optional<Chat> chatCandidate = chatRepository.findById(chatID);
        if(chatCandidate.isEmpty()){
            throw new ApiRequestException("Не удалось найти требуемый чат");
        }

        final Chat chat = chatCandidate.get();

        Message message = Message
                .builder()
                .sender(user)
                .addressee(addressee)
                .chat(chat)
                .messageCreationDate(messageCreationDate)
                .messageCreationTime(messageCreationTime)
                .messageUpdateDate(" ")
                .messageUpdateTime(" ")
                .build();

        messageRepository.save(message);

        Optional<Message> createdMessage = messageRepository.findBySenderAndAddresseeAndMessageCreationDateAndMessageCreationTime(user, addressee, messageCreationDate, messageCreationTime);

        if(createdMessage.isEmpty()) {
            throw new InternalApiException("Не удалось отправить сообщение");
        }

        MessageContent messageContent = MessageContent
                .builder()
                .message(createdMessage.get())
                .messageText(messageText)
                .messageFileLink(" ")
                .build();

        messageContentRepository.save(messageContent);

        return new CreationResponse("Message", createdMessage.get().getId());
    }

    @Override
    public String updateMessage(MessageUpdateRequest messageUpdateRequest) {
        final Long messageID = messageUpdateRequest.getMessageID();
        Optional<Message> messageCandidate = messageRepository.findById(messageID);

        if(messageCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        Message message = messageCandidate.get();
        final String messageUpdateDate = messageUpdateRequest.getMessageUpdateDate();
        final String messageUpdateTime = messageUpdateRequest.getMessageUpdateTime();
        final String updatedMessageText = messageUpdateRequest.getUpdatedMessageText();

        message.setMessageUpdateDate(messageUpdateDate);
        message.setMessageUpdateTime(messageUpdateTime);

        messageRepository.save(message);

        Optional<MessageContent> messageContentCandidate = messageContentRepository.findByMessage(message);

        if(messageContentCandidate.isEmpty()) {
            throw new ApiRequestException("Для выбранного сообщения не найдено содержимое");
        }

        MessageContent messageContent = messageContentCandidate.get();
        messageContent.setMessageText(updatedMessageText);

        messageContentRepository.save(messageContent);

        return "Сообщение было обновлено";
    }
}
