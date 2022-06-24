package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.UserState;

import java.util.Optional;

public interface UserStateRepository extends JpaRepository<UserState, Long>, JpaSpecificationExecutor<UserState> {

    Optional<UserState> findByUserStateName(String userStateName);
}

