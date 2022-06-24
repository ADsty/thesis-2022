package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.AdministrativeOffenceCaseProtocol;

public interface AdministrativeOffenceCaseProtocolRepository extends JpaRepository<AdministrativeOffenceCaseProtocol, Long>, JpaSpecificationExecutor<AdministrativeOffenceCaseProtocol> {
}
