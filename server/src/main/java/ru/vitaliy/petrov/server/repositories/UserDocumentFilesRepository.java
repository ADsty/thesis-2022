package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.UserAccidentDocument;
import ru.vitaliy.petrov.server.models.UserDocumentFiles;

import java.util.List;
import java.util.Optional;

public interface UserDocumentFilesRepository extends JpaRepository<UserDocumentFiles, Long>, JpaSpecificationExecutor<UserDocumentFiles> {

    Optional<List<UserDocumentFiles>> findAllByUserAccidentDocument(UserAccidentDocument userAccidentDocument);

    Optional<UserDocumentFiles> findByFileLink(String fileLink);
}
