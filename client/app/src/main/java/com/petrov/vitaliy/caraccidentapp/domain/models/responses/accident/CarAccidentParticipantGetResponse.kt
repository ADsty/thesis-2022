package com.petrov.vitaliy.caraccidentapp.domain.models.responses.accident

import com.petrov.vitaliy.caraccidentapp.domain.models.responses.user.UserProfileGetResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.user.VehicleProfileGetResponse

data class CarAccidentParticipantGetResponse(
    val participantUserProfile: UserProfileGetResponse,
    val participantVehicle: VehicleProfileGetResponse
)
