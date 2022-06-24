package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.DriverLicense;

import java.util.Optional;

public interface DriverLicenseRepository extends JpaRepository<DriverLicense, Long>, JpaSpecificationExecutor<DriverLicense> {

    Optional<DriverLicense> findByDriverLicenseNumber(String driverLicenseNumber);
}
