package ru.vitaliy.petrov.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vitaliy.petrov.server.error.ApiRequestException;
import ru.vitaliy.petrov.server.error.InternalApiException;
import ru.vitaliy.petrov.server.forms.requests.userprofile.VehicleInsurancePolicyCreationRequest;
import ru.vitaliy.petrov.server.forms.requests.userprofile.VehicleInsurancePolicyUpdateRequest;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.models.Users;
import ru.vitaliy.petrov.server.models.VehicleInsurancePolicy;
import ru.vitaliy.petrov.server.repositories.UsersRepository;
import ru.vitaliy.petrov.server.repositories.VehicleInsurancePolicyRepository;

import java.util.Optional;

@Service
public class VehicleInsurancePolicyService implements IVehicleInsurancePolicyService {

    @Autowired
    private VehicleInsurancePolicyRepository vehicleInsurancePolicyRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public CreationResponse createNewVehicleInsurancePolicy(VehicleInsurancePolicyCreationRequest vehicleInsurancePolicyCreationRequest, Long userID) {
        Optional<Users> userCandidate = usersRepository.findById(userID);

        if (userCandidate.isEmpty()) {
            throw new ApiRequestException("Пользователь, создавший запрос, не был найден");
        }

        final Users user = userCandidate.get();
        final String vehicleInsuranceCompany = vehicleInsurancePolicyCreationRequest.getVehicleInsuranceCompany();
        final String vehicleInsurancePolicyNumber = vehicleInsurancePolicyCreationRequest.getVehicleInsurancePolicyNumber();
        final String vehicleInsurancePolicyExpirationDate = vehicleInsurancePolicyCreationRequest.getVehicleInsurancePolicyExpirationDate();


        VehicleInsurancePolicy vehicleInsurancePolicy = VehicleInsurancePolicy
                .builder()
                .vehicleInsurancePolicyUser(user)
                .vehicleInsuranceCompany(vehicleInsuranceCompany)
                .vehicleInsurancePolicyNumber(vehicleInsurancePolicyNumber)
                .vehicleInsurancePolicyExpirationDate(vehicleInsurancePolicyExpirationDate)
                .build();

        vehicleInsurancePolicyRepository.save(vehicleInsurancePolicy);

        Optional<VehicleInsurancePolicy> createdVehicleInsurancePolicy = vehicleInsurancePolicyRepository.findByVehicleInsurancePolicyNumber(vehicleInsurancePolicyNumber);

        if (createdVehicleInsurancePolicy.isEmpty()) {
            throw new InternalApiException("Не удалось добавить страховку");
        }

        return new CreationResponse("VehicleInsurancePolicy", createdVehicleInsurancePolicy.get().getId());
    }

    @Override
    public VehicleInsurancePolicy getVehicleInsurancePolicy(Long policyID) {
        Optional<VehicleInsurancePolicy> vehicleInsurancePolicyCandidate = vehicleInsurancePolicyRepository.findById(policyID);
        if (vehicleInsurancePolicyCandidate.isEmpty()) {
            throw new ApiRequestException("Страховка не найдена");
        }
        return vehicleInsurancePolicyCandidate.get();
    }

    @Override
    public String updateVehicleInsurancePolicy(VehicleInsurancePolicyUpdateRequest vehicleInsurancePolicyUpdateRequest, Long policyID) {

        final String updatedVehicleInsuranceCompany = vehicleInsurancePolicyUpdateRequest.getUpdatedVehicleInsuranceCompany();
        final String updatedVehicleInsurancePolicyNumber = vehicleInsurancePolicyUpdateRequest.getUpdatedVehicleInsurancePolicyNumber();
        final String updatedVehicleInsurancePolicyExpirationDate = vehicleInsurancePolicyUpdateRequest.getUpdatedVehicleInsurancePolicyExpirationDate();

        Optional<VehicleInsurancePolicy> vehicleInsurancePolicyCandidate = vehicleInsurancePolicyRepository.findById(policyID);
        if (vehicleInsurancePolicyCandidate.isEmpty()) {
            throw new ApiRequestException("Страховка не найдена");
        }
        VehicleInsurancePolicy vehicleInsurancePolicy = vehicleInsurancePolicyCandidate.get();

        vehicleInsurancePolicy.setVehicleInsuranceCompany(updatedVehicleInsuranceCompany);
        vehicleInsurancePolicy.setVehicleInsurancePolicyNumber(updatedVehicleInsurancePolicyNumber);
        vehicleInsurancePolicy.setVehicleInsurancePolicyExpirationDate(updatedVehicleInsurancePolicyExpirationDate);

        vehicleInsurancePolicyRepository.save(vehicleInsurancePolicy);
        return "Пользователь был изменен";
    }

    @Override
    public String deleteVehicleInsurancePolicy(Long policyID) {
        Optional<VehicleInsurancePolicy> vehicleInsurancePolicyCandidate = vehicleInsurancePolicyRepository.findById(policyID);
        if (vehicleInsurancePolicyCandidate.isEmpty()) {
            throw new ApiRequestException("Страховка не найдена");
        }
        vehicleInsurancePolicyRepository.delete(vehicleInsurancePolicyCandidate.get());
        return "Страховка была удалена";
    }
}
