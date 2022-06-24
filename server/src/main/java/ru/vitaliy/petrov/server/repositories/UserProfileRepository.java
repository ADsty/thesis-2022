package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.UserProfile;
import ru.vitaliy.petrov.server.models.Users;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long>, JpaSpecificationExecutor<UserProfile> {

    Optional<UserProfile> findByUser(Users user);
}
