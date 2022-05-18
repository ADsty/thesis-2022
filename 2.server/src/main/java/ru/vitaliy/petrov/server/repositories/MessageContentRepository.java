package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.Message;
import ru.vitaliy.petrov.server.models.MessageContent;

import java.util.Optional;

public interface MessageContentRepository extends JpaRepository<MessageContent, Long>, JpaSpecificationExecutor<MessageContent>  {

    Optional<MessageContent> findByMessage(Message message);
}
