package com.petrov.vitaliy.caraccidentapp.domain.repository

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.VehicleProfileCreationRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.VehicleProfileUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.CreationResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.StringResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.user.VehicleProfileGetResponse

interface VehicleProfileRepository {

    fun createVehicleProfile(
        jwtToken: String,
        vehicleProfileCreationRequest: VehicleProfileCreationRequest
    ): Result<CreationResponse>

    fun getUsersVehicleProfiles(jwtToken: String): Result<Array<VehicleProfileGetResponse>>

    fun updateVehicleProfile(
        jwtToken: String,
        vehicleProfileUpdateRequest: VehicleProfileUpdateRequest,
        vehicleID: Long
    ): Result<StringResponse>

    fun deleteVehicleProfile(jwtToken: String, vehicleID: Long): Result<StringResponse>

}