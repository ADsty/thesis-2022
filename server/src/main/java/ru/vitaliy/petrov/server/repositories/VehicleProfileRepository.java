package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.Users;
import ru.vitaliy.petrov.server.models.VehicleProfile;

import java.util.List;
import java.util.Optional;

public interface VehicleProfileRepository extends JpaRepository<VehicleProfile, Long>, JpaSpecificationExecutor<VehicleProfile> {

    Optional<VehicleProfile> findByVehicleProfileUserAndVehicleVin(Users vehicleProfileUser, String vehicleVIN);

    Optional<List<VehicleProfile>> findAllByVehicleProfileUser(Users user);
}
