package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.CarAccident;
import ru.vitaliy.petrov.server.models.CarAccidentEntity;

import java.util.List;
import java.util.Optional;

public interface CarAccidentEntityRepository extends JpaRepository<CarAccidentEntity, Long>, JpaSpecificationExecutor<CarAccidentEntity> {

    Optional<CarAccidentEntity> findByCarAccident(CarAccident carAccident);
}
