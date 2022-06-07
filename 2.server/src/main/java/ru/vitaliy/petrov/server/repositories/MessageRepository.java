package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.Chat;
import ru.vitaliy.petrov.server.models.Message;
import ru.vitaliy.petrov.server.models.Users;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long>, JpaSpecificationExecutor<Message> {

    List<Message> findAllByChat(Chat chat);

    Optional<Message> findBySenderAndAddresseeAndMessageCreationDateAndMessageCreationTime(Users sender, Users addressee, Date messageCreationDate, Time messageCreationTime);

    Optional<Message> findTopByChatOrderByIdDesc(Chat chat);
}
