package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.CarAccidentEntityDocuments;
import ru.vitaliy.petrov.server.models.UserAccidentDocument;
import ru.vitaliy.petrov.server.models.Users;

import java.util.Optional;

public interface UserAccidentDocumentRepository extends JpaRepository<UserAccidentDocument, Long>, JpaSpecificationExecutor<UserAccidentDocument> {

    Optional<UserAccidentDocument> findBySenderParticipantAndCarAccidentEntityDocuments(Users sender, CarAccidentEntityDocuments carAccidentEntityDocuments);
}
