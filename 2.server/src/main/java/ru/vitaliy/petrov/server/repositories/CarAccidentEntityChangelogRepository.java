package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.CarAccidentEntity;
import ru.vitaliy.petrov.server.models.CarAccidentEntityChangelog;

import java.util.List;

public interface CarAccidentEntityChangelogRepository extends JpaRepository<CarAccidentEntityChangelog, Long>, JpaSpecificationExecutor<CarAccidentEntityChangelog> {

    List<CarAccidentEntityChangelog> findAllByCarAccidentEntity(CarAccidentEntity carAccidentEntity);
}
