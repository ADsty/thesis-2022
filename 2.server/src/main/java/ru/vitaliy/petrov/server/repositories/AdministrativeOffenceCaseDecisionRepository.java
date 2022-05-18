package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.AdministrativeOffenceCaseDecision;

public interface AdministrativeOffenceCaseDecisionRepository extends JpaRepository<AdministrativeOffenceCaseDecision, Long>, JpaSpecificationExecutor<AdministrativeOffenceCaseDecision> {

}
