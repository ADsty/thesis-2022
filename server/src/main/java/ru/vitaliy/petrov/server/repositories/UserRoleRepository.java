package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.UserRole;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>, JpaSpecificationExecutor<UserRole> {

    Optional<UserRole> findByUserRoleName(String userRoleName);
}
