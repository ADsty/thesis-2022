package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.CarAccident;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

public interface CarAccidentRepository extends JpaRepository<CarAccident, Long>, JpaSpecificationExecutor<CarAccident> {

    Optional<CarAccident> findByCarAccidentSceneAndCarAccidentDateAndCarAccidentTime(String accidentScene, Date accidentDate, Time accidentTime);
}
