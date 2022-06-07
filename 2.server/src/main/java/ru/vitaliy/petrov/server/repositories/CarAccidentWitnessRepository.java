package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.CarAccidentEntity;
import ru.vitaliy.petrov.server.models.CarAccidentWitness;

import java.util.List;
import java.util.Optional;

public interface CarAccidentWitnessRepository extends JpaRepository<CarAccidentWitness, Long>, JpaSpecificationExecutor<CarAccidentWitness> {

    Optional<CarAccidentWitness> findByCarAccidentEntityAndWitnessFullName(CarAccidentEntity entity, String witnessFullName);

    Optional<List<CarAccidentWitness>> findAllByCarAccidentEntity(CarAccidentEntity entity);
}
