package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.CarAccidentEntity;
import ru.vitaliy.petrov.server.models.CarAccidentEntityDocuments;

import java.util.Optional;

public interface CarAccidentEntityDocumentsRepository extends JpaRepository<CarAccidentEntityDocuments, Long>, JpaSpecificationExecutor<CarAccidentEntityDocuments> {

    Optional<CarAccidentEntityDocuments> findByCarAccidentEntity(CarAccidentEntity carAccidentEntity);
}
