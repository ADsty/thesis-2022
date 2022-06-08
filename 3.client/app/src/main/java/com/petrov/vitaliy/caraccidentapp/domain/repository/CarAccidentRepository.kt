package com.petrov.vitaliy.caraccidentapp.domain.repository

import com.petrov.vitaliy.caraccidentapp.domain.models.requests.accident.*
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.accident.*
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.CreationResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.StringResponse
import java.sql.Date

interface CarAccidentRepository {

    fun createCarAccident(
        jwtToken: String,
        carAccidentCreationRequest: CarAccidentCreationRequest
    ): Result<CreationResponse>

    fun getAllUsersCarAccidents(jwtToken: String): Result<Array<CarAccidentEntityGetResponse>>

    fun getAllOfficerCarAccidents(jwtToken: String): Result<Array<CarAccidentEntityGetResponse>>

    fun getCarAccident(jwtToken: String, carAccidentID: Long): Result<CarAccidentGetResponse>

    fun getCarAccidentChangelog(
        jwtToken: String,
        carAccidentID: Long,
        changeDate: Date
    ): Result<Array<CarAccidentEntityChangelogGetResponse>>

    fun updateCarAccident(
        jwtToken: String,
        carAccidentUpdateRequest: CarAccidentUpdateRequest,
        carAccidentID: Long
    ): Result<StringResponse>

    fun addCarAccidentParticipant(
        jwtToken: String,
        carAccidentParticipantAddRequest: CarAccidentParticipantAddRequest
    ): Result<CreationResponse>

    fun getCarAccidentParticipants(
        jwtToken: String,
        entityID: Long
    ): Result<Array<CarAccidentParticipantGetResponse>>

    fun deleteCarAccidentParticipant(
        jwtToken: String,
        carAccidentParticipantDeleteRequest: CarAccidentParticipantDeleteRequest
    ): Result<StringResponse>

    fun addCarAccidentWitness(
        jwtToken: String,
        carAccidentWitnessAddRequest: CarAccidentWitnessAddRequest
    ): Result<CreationResponse>

    fun getCarAccidentWitnesses(
        jwtToken: String,
        entityID: Long
    ): Result<Array<CarAccidentWitnessGetResponse>>

    fun updateCarAccidentWitness(
        jwtToken: String,
        carAccidentWitnessUpdateRequest: CarAccidentWitnessUpdateRequest
    ): Result<StringResponse>

    fun deleteCarAccidentWitness(
        jwtToken: String,
        carAccidentWitnessDeleteRequest: CarAccidentWitnessDeleteRequest
    ): Result<StringResponse>

    fun changeCarAccidentState(
        jwtToken: String,
        entityID: Long,
        entityState: String
    ): Result<StringResponse>

}