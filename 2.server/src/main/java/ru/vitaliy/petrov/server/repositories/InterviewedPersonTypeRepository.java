package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.InterviewedPersonType;

public interface InterviewedPersonTypeRepository extends JpaRepository<InterviewedPersonType, Long>, JpaSpecificationExecutor<InterviewedPersonType> {
}
