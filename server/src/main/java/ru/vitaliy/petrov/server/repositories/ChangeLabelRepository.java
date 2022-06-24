package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.ChangeLabel;

import java.util.Optional;

public interface ChangeLabelRepository extends JpaRepository<ChangeLabel, Long>, JpaSpecificationExecutor<ChangeLabel> {

    Optional<ChangeLabel> findByChangeLabel(String changeLabel);
}
