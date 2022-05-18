package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.WitnessesAdditionalInfo;

public interface WitnessesAdditionalInfoRepository extends JpaRepository<WitnessesAdditionalInfo, Long>, JpaSpecificationExecutor<WitnessesAdditionalInfo> {
}
