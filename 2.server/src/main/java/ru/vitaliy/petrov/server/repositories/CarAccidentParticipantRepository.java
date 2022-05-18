package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.CarAccidentEntity;
import ru.vitaliy.petrov.server.models.CarAccidentParticipant;
import ru.vitaliy.petrov.server.models.Users;

import java.util.List;
import java.util.Optional;

public interface CarAccidentParticipantRepository extends JpaRepository<CarAccidentParticipant, Long>, JpaSpecificationExecutor<CarAccidentParticipant>  {

    Optional<CarAccidentParticipant> findByCarAccidentParticipant(Users carAccidentParticipant);

    List<CarAccidentParticipant> findAllByCarAccidentEntity(CarAccidentEntity carAccidentEntity);
}
