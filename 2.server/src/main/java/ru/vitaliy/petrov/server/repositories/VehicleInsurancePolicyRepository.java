package ru.vitaliy.petrov.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.vitaliy.petrov.server.models.VehicleInsurancePolicy;

import java.util.Optional;

public interface VehicleInsurancePolicyRepository extends JpaRepository<VehicleInsurancePolicy, Long>, JpaSpecificationExecutor<VehicleInsurancePolicy> {

    Optional<VehicleInsurancePolicy> findByVehicleInsurancePolicyNumber(String vehicleInsurancePolicyNumber);
}
