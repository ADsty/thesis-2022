package com.petrov.vitaliy.caraccidentapp.domain.repository

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.VehicleInsurancePolicyCreationRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.VehicleInsurancePolicyUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.CreationResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.StringResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.user.VehicleInsurancePolicyGetResponse

interface VehicleInsurancePolicyRepository {

    fun createVehicleInsurancePolicy(
        jwtToken: String,
        vehicleInsurancePolicyCreationRequest: VehicleInsurancePolicyCreationRequest
    ): Result<CreationResponse>

    fun getVehicleInsurancePolicy(
        jwtToken: String,
        policyID: Long
    ): Result<VehicleInsurancePolicyGetResponse>

    fun updateVehicleInsurancePolicy(
        jwtToken: String,
        vehicleInsurancePolicyUpdateRequest: VehicleInsurancePolicyUpdateRequest
    ): Result<StringResponse>

    fun deleteVehicleInsurancePolicy(jwtToken: String, policyID: Long): Result<StringResponse>

}