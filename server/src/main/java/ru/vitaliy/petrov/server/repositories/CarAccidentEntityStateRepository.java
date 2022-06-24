package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.CarAccidentEntityState;

import java.util.Optional;

public interface CarAccidentEntityStateRepository extends JpaRepository<CarAccidentEntityState, Long>, JpaSpecificationExecutor<CarAccidentEntityState> {

    Optional<CarAccidentEntityState> findByCarAccidentEntityState(String carAccidentEntityState);
}
