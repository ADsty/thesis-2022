package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.CarAccidentEntityDocuments;
import ru.vitaliy.petrov.server.models.ConfiscationOfDocumentsProtocol;
import ru.vitaliy.petrov.server.models.Users;

import java.util.Optional;

public interface ConfiscationOfDocumentsProtocolRepository extends JpaRepository<ConfiscationOfDocumentsProtocol, Long>, JpaSpecificationExecutor<ConfiscationOfDocumentsProtocol> {

    Optional<ConfiscationOfDocumentsProtocol> findByCarAccidentEntityDocumentsAndCarAccidentParticipant(CarAccidentEntityDocuments carAccidentEntityDocuments, Users userID);
}
