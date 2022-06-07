package ru.vitaliy.petrov.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vitaliy.petrov.server.error.ApiRequestException;
import ru.vitaliy.petrov.server.error.ForbiddenApiException;
import ru.vitaliy.petrov.server.error.InternalApiException;
import ru.vitaliy.petrov.server.forms.requests.userprofile.VehicleProfileCreationRequest;
import ru.vitaliy.petrov.server.forms.requests.userprofile.VehicleProfileUpdateRequest;
import ru.vitaliy.petrov.server.forms.responses.CreationResponse;
import ru.vitaliy.petrov.server.models.Users;
import ru.vitaliy.petrov.server.models.VehicleInsurancePolicy;
import ru.vitaliy.petrov.server.models.VehicleProfile;
import ru.vitaliy.petrov.server.repositories.UsersRepository;
import ru.vitaliy.petrov.server.repositories.VehicleInsurancePolicyRepository;
import ru.vitaliy.petrov.server.repositories.VehicleProfileRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleProfileService implements IVehicleProfileService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private VehicleProfileRepository vehicleProfileRepository;

    @Autowired
    private VehicleInsurancePolicyRepository vehicleInsurancePolicyRepository;

    @Override
    public CreationResponse createNewVehicleProfile(VehicleProfileCreationRequest vehicleProfileCreationRequest, Long userID) {
        Optional<Users> userCandidate = usersRepository.findById(userID);

        if (userCandidate.isEmpty()) {
            throw new ApiRequestException("Пользователь, создавший запрос, не был найден");
        }

        final Users user = userCandidate.get();
        final String vehicleBrand = vehicleProfileCreationRequest.getVehicleBrand();
        final String vehicleVIN = vehicleProfileCreationRequest.getVehicleVIN();
        final String vehicleRegistrationSign = vehicleProfileCreationRequest.getVehicleRegistrationSign();
        final String vehicleRegistrationCertificate = vehicleProfileCreationRequest.getVehicleRegistrationCertificate();
        final String vehicleOwnerFullName = vehicleProfileCreationRequest.getVehicleOwnerFullName();
        final String vehicleOwnerResidentialAddress = vehicleProfileCreationRequest.getVehicleOwnerResidentialAddress();
        final String vehicleInsurancePolicyNumber = vehicleProfileCreationRequest.getVehicleInsurancePolicyNumber();

        if (vehicleBrand == null || vehicleVIN == null || vehicleRegistrationSign == null || vehicleRegistrationCertificate == null || vehicleOwnerFullName == null || vehicleOwnerResidentialAddress == null) {
            throw new ApiRequestException("Введенные данные некорректны");
        }

        Optional<VehicleInsurancePolicy> vehicleInsurancePolicyCandidate = vehicleInsurancePolicyRepository.findByVehicleInsurancePolicyNumber(vehicleInsurancePolicyNumber);

        if (vehicleInsurancePolicyCandidate.isEmpty()) {
            throw new ApiRequestException("Страховка машины не была найдена");
        }

        VehicleInsurancePolicy vehicleInsurancePolicy = vehicleInsurancePolicyCandidate.get();

        VehicleProfile vehicleProfile = VehicleProfile
                .builder()
                .vehicleProfileUser(user)
                .vehicleBrand(vehicleBrand)
                .vehicleVin(vehicleVIN)
                .vehicleRegistrationSign(vehicleRegistrationSign)
                .vehicleRegistrationCertificate(vehicleRegistrationCertificate)
                .vehicleOwnerFullName(vehicleOwnerFullName)
                .vehicleOwnerResidentialAddress(vehicleOwnerResidentialAddress)
                .vehicleDriver(user)
                .vehicleInsurancePolicy(vehicleInsurancePolicy)
                .build();

        vehicleProfileRepository.save(vehicleProfile);

        Optional<VehicleProfile> createdVehicleProfile = vehicleProfileRepository.findByVehicleProfileUserAndVehicleVin(user, vehicleVIN);

        if (createdVehicleProfile.isEmpty()) {
            throw new InternalApiException("Не удалось добавить профиль машины");
        }

        return new CreationResponse("VehicleProfile", createdVehicleProfile.get().getId());
    }

    @Override
    public List<VehicleProfile> getAllVehicleProfiles(Long userID) {
        Optional<Users> userCandidate = usersRepository.findById(userID);

        if (userCandidate.isEmpty()) {
            throw new ApiRequestException("Пользователь не был найден");
        }

        Users user = userCandidate.get();

        Optional<List<VehicleProfile>> allVehicleProfilesCandidate = vehicleProfileRepository.findAllByVehicleProfileUser(user);

        if (allVehicleProfilesCandidate.isEmpty()) {
            throw new ApiRequestException("Профили машин данного пользователя не были найдены");
        }

        return allVehicleProfilesCandidate.get();
    }

    @Override
    public String updateVehicleProfile(VehicleProfileUpdateRequest vehicleProfileUpdateRequest, Long vehicleID, Long userID) {

        final String updatedVehicleBrand = vehicleProfileUpdateRequest.getUpdatedVehicleBrand();
        final String updatedVehicleVIN = vehicleProfileUpdateRequest.getUpdatedVehicleVIN();
        final String updatedVehicleRegistrationSign = vehicleProfileUpdateRequest.getUpdatedVehicleRegistrationSign();
        final String updatedVehicleRegistrationCertificate = vehicleProfileUpdateRequest.getUpdatedVehicleRegistrationCertificate();
        final String updatedVehicleOwnerFullName = vehicleProfileUpdateRequest.getUpdatedVehicleOwnerFullName();
        final String updatedVehicleOwnerResidentialAddress = vehicleProfileUpdateRequest.getUpdatedVehicleOwnerResidentialAddress();
        final String updatedVehicleInsurancePolicyNumber = vehicleProfileUpdateRequest.getUpdatedVehicleInsurancePolicyNumber();

        Optional<VehicleInsurancePolicy> vehicleInsurancePolicyCandidate = vehicleInsurancePolicyRepository.findByVehicleInsurancePolicyNumber(updatedVehicleInsurancePolicyNumber);

        if (vehicleInsurancePolicyCandidate.isEmpty()) {
            throw new ApiRequestException("Введенные данные неверны");
        }

        final VehicleInsurancePolicy updatedVehicleInsurancePolicy = vehicleInsurancePolicyCandidate.get();

        Optional<VehicleProfile> vehicleProfileCandidate = vehicleProfileRepository.findById(vehicleID);

        if (vehicleProfileCandidate.isEmpty()) {
            throw new ApiRequestException("Профиль машины не найден");
        }

        if (!vehicleProfileCandidate.get().getVehicleProfileUser().getId().equals(userID)) {
            throw new ForbiddenApiException("У вас нет доступа к данному профилю машины");
        }

        VehicleProfile vehicleProfile = vehicleProfileCandidate.get();

        vehicleProfile.setVehicleBrand(updatedVehicleBrand);
        vehicleProfile.setVehicleVin(updatedVehicleVIN);
        vehicleProfile.setVehicleRegistrationSign(updatedVehicleRegistrationSign);
        vehicleProfile.setVehicleRegistrationCertificate(updatedVehicleRegistrationCertificate);
        vehicleProfile.setVehicleOwnerFullName(updatedVehicleOwnerFullName);
        vehicleProfile.setVehicleOwnerResidentialAddress(updatedVehicleOwnerResidentialAddress);
        vehicleProfile.setVehicleInsurancePolicy(updatedVehicleInsurancePolicy);


        vehicleProfileRepository.save(vehicleProfile);
        return "Профиль машины был изменен";
    }

    @Override
    public String deleteVehicleProfile(Long vehicleID, Long userID) {
        Optional<VehicleProfile> vehicleProfileCandidate = vehicleProfileRepository.findById(vehicleID);

        if (vehicleProfileCandidate.isEmpty()) {
            throw new ApiRequestException("Такого профиля машины не существует");
        }

        if (!vehicleProfileCandidate.get().getVehicleProfileUser().getId().equals(userID)) {
            throw new ForbiddenApiException("У вас нет доступа к данному профилю машины");
        }

        VehicleProfile vehicleProfile = vehicleProfileCandidate.get();

        vehicleProfileRepository.delete(vehicleProfile);

        return "Профиль машины был удален";
    }

}
