package com.petrov.vitaliy.caraccidentapp.domain.models.requests.user

data class UserLoginRequest(val phoneNumber: String, val password: String)