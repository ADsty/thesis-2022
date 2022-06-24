package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.CarAccidentEntity;
import ru.vitaliy.petrov.server.models.CarAccidentEntityChangelog;
import ru.vitaliy.petrov.server.models.ChangeLabel;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface CarAccidentEntityChangelogRepository extends JpaRepository<CarAccidentEntityChangelog, Long>, JpaSpecificationExecutor<CarAccidentEntityChangelog> {

    Optional<List<CarAccidentEntityChangelog>> findAllByCarAccidentEntityAndChangeDateAndChangeLabel(CarAccidentEntity carAccidentEntity, Date changeDate, ChangeLabel changeLabel);
}
