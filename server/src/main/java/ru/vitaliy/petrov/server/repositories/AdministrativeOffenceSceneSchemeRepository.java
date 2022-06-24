package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.AdministrativeOffenceSceneScheme;

public interface AdministrativeOffenceSceneSchemeRepository extends JpaRepository<AdministrativeOffenceSceneScheme, Long>, JpaSpecificationExecutor<AdministrativeOffenceSceneScheme> {
}
