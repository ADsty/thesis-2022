package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.CarAccidentEntityDocuments;
import ru.vitaliy.petrov.server.models.ExplanationDocument;
import ru.vitaliy.petrov.server.models.Users;

import java.util.Optional;

public interface ExplanationDocumentRepository extends JpaRepository<ExplanationDocument, Long>, JpaSpecificationExecutor<ExplanationDocument> {

    Optional<ExplanationDocument> findByCarAccidentEntityDocumentsAndCarAccidentParticipant(CarAccidentEntityDocuments carAccidentEntityDocuments, Users userID);
}
