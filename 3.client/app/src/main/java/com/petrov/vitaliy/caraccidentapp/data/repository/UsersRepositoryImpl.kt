package com.petrov.vitaliy.caraccidentapp.data.repository

import com.petrov.vitaliy.caraccidentapp.data.*
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.UserLoginRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.UserRegistrationRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.StringResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.TokenResponse
import com.petrov.vitaliy.caraccidentapp.domain.repository.UsersRepository
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


class UsersRepositoryImpl : UsersRepository {


    override fun register(userRegistrationRequest: UserRegistrationRequest): Result<TokenResponse> {

        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("register")
            .addQueryParameter("phoneNumber", userRegistrationRequest.phoneNumber)
            .addQueryParameter("password", userRegistrationRequest.password)
            .addQueryParameter("roleName", "USER")
            .build()

        val request = Request.Builder().post(body).url(httpBuilder).build()

        return executeTokenRequest(request)
    }

    override fun login(userLoginRequest: UserLoginRequest): Result<TokenResponse> {

        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("login")
            .addQueryParameter("phoneNumber", userLoginRequest.phoneNumber)
            .addQueryParameter("password", userLoginRequest.password)
            .build()

        val request = Request.Builder().post(body).url(httpBuilder).build()

        return executeTokenRequest(request)
    }

    override fun getConfirmationCode(phoneNumber: String): Result<StringResponse> {

        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("get-confirmation-code")
            .addQueryParameter("phoneNumber", phoneNumber)
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", " ")
        }.build()

        return executeStringRequest(request)
    }

    override fun checkConfirmationCode(
        phoneNumber: String,
        confirmationCode: Int
    ): Result<StringResponse> {

        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("check-confirmation-code")
            .addQueryParameter("phoneNumber", phoneNumber)
            .addQueryParameter("confirmationCode", confirmationCode.toString())
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", " ")
        }.build()

        return executeStringRequest(request)

    }

    override fun updateUser(phoneNumber: String, password: String): Result<TokenResponse> {

        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("update-user")
            .addQueryParameter("phoneNumber", phoneNumber)
            .addQueryParameter("password", password)
            .build()

        val request = Request.Builder().patch(body).url(httpBuilder).apply {
            addHeader("Authorization", " ")
        }.build()

        return executeTokenRequest(request)

    }

    override fun getUserRole(jwtToken: String): Result<StringResponse> {

        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("get-user-role")
            .build()

        val request = Request.Builder().get().url(httpBuilder).build()

        return executeStringRequest(request)

    }

    override fun changeUserState(jwtToken: String, stateName: String): Result<StringResponse> {

        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("change-state")
            .addQueryParameter("stateName", stateName)
            .build()

        val request = Request.Builder().patch(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)

    }

}