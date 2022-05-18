package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.CarAccidentWitness;

public interface CarAccidentWitnessRepository extends JpaRepository<CarAccidentWitness, Long>, JpaSpecificationExecutor<CarAccidentWitness> {
}
