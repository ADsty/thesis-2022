package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.CulpritAdditionalInfo;

public interface CulpritAdditionalInfoRepository extends JpaRepository<CulpritAdditionalInfo, Long>, JpaSpecificationExecutor<CulpritAdditionalInfo> {
}
