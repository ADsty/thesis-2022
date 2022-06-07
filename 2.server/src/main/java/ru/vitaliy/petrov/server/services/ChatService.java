package ru.vitaliy.petrov.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vitaliy.petrov.server.error.ApiRequestException;
import ru.vitaliy.petrov.server.error.ForbiddenApiException;
import ru.vitaliy.petrov.server.error.InternalApiException;
import ru.vitaliy.petrov.server.forms.requests.chats.MessageCreationRequest;
import ru.vitaliy.petrov.server.forms.requests.chats.MessageUpdateRequest;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.models.*;
import ru.vitaliy.petrov.server.repositories.ChatRepository;
import ru.vitaliy.petrov.server.repositories.MessageContentRepository;
import ru.vitaliy.petrov.server.repositories.MessageRepository;
import ru.vitaliy.petrov.server.repositories.UsersRepository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService implements IChatService {

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
        if (userCandidate.isEmpty()) {
            throw new ApiRequestException("Не удалось найти пользователя");
        }
        Users user = userCandidate.get();
        return chatRepository.findAllByCarAccidentParticipant(user);
    }

    public List<Chat> getAllChatsOfPoliceOfficer(Long officerID) {
        Optional<Users> userCandidate = usersRepository.findById(officerID);
        if (userCandidate.isEmpty()) {
            throw new ApiRequestException("Не удалось найти пользователя");
        }
        Users user = userCandidate.get();
        return chatRepository.findAllByTrafficPoliceOfficer(user);
    }

    @Override
    public List<Message> getAllMessagesOfChat(Long chatID, Long userID) {
        Optional<Chat> chatCandidate = chatRepository.findById(chatID);
        if (chatCandidate.isEmpty()) {
            throw new ApiRequestException("Не удалось найти требуемый чат");
        }
        if (!chatCandidate.get().getCarAccidentParticipant().getId().equals(userID) && !chatCandidate.get().getTrafficPoliceOfficer().getId().equals(userID)) {
            throw new ForbiddenApiException("Вы не имеете доступа к данному чату");
        }
        return messageRepository.findAllByChat(chatCandidate.get());
    }

    @Override
    public CreationResponse sendMessage(MessageCreationRequest messageCreationRequest, Long userID) {

        final Long chatID = messageCreationRequest.getChatID();

        Optional<Chat> chatCandidate = chatRepository.findById(chatID);

        if (chatCandidate.isEmpty()) {
            throw new ApiRequestException("Не удалось найти требуемый чат");
        }

        if (isCarAccidentClosed(chatCandidate.get().getCarAccidentEntity())) {
            throw new ForbiddenApiException("Данный чат закрыт");
        }

        Optional<Users> userCandidate = usersRepository.findById(userID);
        if (userCandidate.isEmpty()) {
            throw new ApiRequestException("Не удалось найти пользователя");
        }

        final Users user = userCandidate.get();
        final Long addresseeID = messageCreationRequest.getAddressee();

        Optional<Users> addresseeCandidate = usersRepository.findById(addresseeID);
        if (addresseeCandidate.isEmpty()) {
            throw new ApiRequestException("Не удалось найти пользователя");
        }

        final Users addressee = addresseeCandidate.get();
        final Date messageCreationDate = messageCreationRequest.getMessageCreationDate();
        final Time messageCreationTime = messageCreationRequest.getMessageCreationTime();
        final String messageText = messageCreationRequest.getMessageText();

        final Chat chat = chatCandidate.get();

        Message message = Message
                .builder()
                .sender(user)
                .addressee(addressee)
                .chat(chat)
                .messageCreationDate(messageCreationDate)
                .messageCreationTime(messageCreationTime)
                .messageUpdateDate(null)
                .messageUpdateTime(null)
                .build();

        messageRepository.save(message);

        Optional<Message> createdMessage = messageRepository.findBySenderAndAddresseeAndMessageCreationDateAndMessageCreationTime(user, addressee, messageCreationDate, messageCreationTime);

        if (createdMessage.isEmpty()) {
            throw new InternalApiException("Не удалось отправить сообщение");
        }

        MessageContent messageContent = MessageContent
                .builder()
                .message(createdMessage.get())
                .messageText(messageText)
                .build();

        messageContentRepository.save(messageContent);

        return new CreationResponse("Message", createdMessage.get().getId());
    }

    @Override
    public String updateMessage(MessageUpdateRequest messageUpdateRequest, Long userID) {
        final Long messageID = messageUpdateRequest.getMessageID();
        Optional<Message> messageCandidate = messageRepository.findById(messageID);

        if (messageCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        if (!messageCandidate.get().getSender().getId().equals(userID)) {
            throw new ForbiddenApiException("ВЫ не имеете доступа к изменению данного сообщения");
        }

        if (isCarAccidentClosed(messageCandidate.get().getChat().getCarAccidentEntity())) {
            throw new ForbiddenApiException("Данный чат закрыт для изменений");
        }

        Message message = messageCandidate.get();
        final Date messageUpdateDate = messageUpdateRequest.getMessageUpdateDate();
        final Time messageUpdateTime = messageUpdateRequest.getMessageUpdateTime();
        final String updatedMessageText = messageUpdateRequest.getUpdatedMessageText();

        message.setMessageUpdateDate(messageUpdateDate);
        message.setMessageUpdateTime(messageUpdateTime);

        messageRepository.save(message);

        Optional<MessageContent> messageContentCandidate = messageContentRepository.findByMessage(message);

        if (messageContentCandidate.isEmpty()) {
            throw new ApiRequestException("Для выбранного сообщения не найдено содержимое");
        }

        MessageContent messageContent = messageContentCandidate.get();
        messageContent.setMessageText(updatedMessageText);

        messageContentRepository.save(messageContent);

        return "Сообщение было обновлено";
    }

    public MessageContent getMessageContent(Message message) {
        Optional<MessageContent> messageContentCandidate = messageContentRepository.findByMessage(message);
        if (messageContentCandidate.isEmpty()) {
            throw new ApiRequestException("У данного сообщения нет контента");
        }
        return messageContentCandidate.get();
    }

    private boolean isCarAccidentClosed(CarAccidentEntity carAccidentEntity) {
        return carAccidentEntity.getEntityState().getCarAccidentEntityState().equals("Закрытое заявление");
    }

    public Message getLastMessageOfChat(Chat chat) {
        Optional<Message> lastMessageCandidate = messageRepository.findTopByChatOrderByIdDesc(chat);

        if (lastMessageCandidate.isEmpty()) {
            return null;
        }

        return lastMessageCandidate.get();
    }

}
