package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.DriverLicenseCategory;

import java.util.Optional;

public interface DriverLicenseCategoryRepository extends JpaRepository<DriverLicenseCategory, Long>, JpaSpecificationExecutor<DriverLicenseCategory> {

    Optional<DriverLicenseCategory> findByDriverLicenseCategory(String driverLicenseCategory);
}
