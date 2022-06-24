package com.petrov.vitaliy.caraccidentapp.domain.models.responses.general

data class ApiException(
    val error: Boolean,
    val description: String,
    val httpStatus: String
)