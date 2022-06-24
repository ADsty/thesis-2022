package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.AdministrativeOffenceSceneInspection;

public interface AdministrativeOffenceSceneInspectionRepository extends JpaRepository<AdministrativeOffenceSceneInspection, Long>, JpaSpecificationExecutor<AdministrativeOffenceSceneInspection> {
}
