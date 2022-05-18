package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.AdministrativeOffenceCaseInvestigation;

public interface AdministrativeOffenceCaseInvestigationRepository extends JpaRepository<AdministrativeOffenceCaseInvestigation, Long>, JpaSpecificationExecutor<AdministrativeOffenceCaseInvestigation> {
}
