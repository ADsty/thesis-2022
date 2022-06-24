package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.AdministrativeOffenceCaseRefusal;

public interface AdministrativeOffenceCaseRefusalRepository extends JpaRepository<AdministrativeOffenceCaseRefusal, Long>, JpaSpecificationExecutor<AdministrativeOffenceCaseRefusal> {
}
