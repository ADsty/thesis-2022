package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.UserState;
import ru.vitaliy.petrov.server.models.Users;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long>, JpaSpecificationExecutor<Users> {

    Optional<Users> findByUserPhoneNumber(String userPhoneNumber);

    Optional<Users> findFirstByUserState(UserState userState);
}
