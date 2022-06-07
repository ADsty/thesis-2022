package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.Chat;
import ru.vitaliy.petrov.server.models.Users;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long>, JpaSpecificationExecutor<Chat> {

    List<Chat> findAllByCarAccidentParticipant(Users user);

    List<Chat> findAllByTrafficPoliceOfficer(Users officer);
}
