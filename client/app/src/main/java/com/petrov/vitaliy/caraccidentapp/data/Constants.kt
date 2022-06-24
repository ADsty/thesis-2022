package com.petrov.vitaliy.caraccidentapp.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.ApiException
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.CreationResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.StringResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.TokenResponse
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.text.DateFormat
import java.util.concurrent.TimeUnit

const val scheme = "http"

const val host = "10.0.2.2"

const val port = 8832

val gson: Gson = GsonBuilder()
    .serializeNulls()
    .setDateFormat(DateFormat.SHORT, DateFormat.SHORT)
    .create()

val httpClient = OkHttpClient()
    .newBuilder()
    .connectTimeout(1, TimeUnit.HOURS)
    .readTimeout(1, TimeUnit.HOURS)
    .build()


fun executeRequest(request: Request): Response? {
    val response: Response

    try {
        response = httpClient.newCall(request).execute()
    } catch (e: IOException) {
        e.printStackTrace()
        return null
    }
    return response
}

fun executeTokenRequest(request: Request): Result<TokenResponse> {
    val response = executeRequest(request)
        ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

    if (response.code != 200) {
        val exception: ApiException =
            gson.fromJson(response.body?.string(), ApiException::class.java)
        return Result.failure(IOException(exception.error.toString() + " " + exception.description + " " + exception.httpStatus))
    }

    val result = gson.fromJson(response.body?.string(), TokenResponse::class.java)
    return Result.success(result)
}

fun executeStringRequest(request: Request): Result<StringResponse> {
    val response = executeRequest(request)
        ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

    if (response.code != 200) {
        val exception: ApiException =
            gson.fromJson(response.body?.string(), ApiException::class.java)
        return Result.failure(IOException(response.body?.string() + " " + exception.description + " " + exception.httpStatus))
    }

    val result = gson.fromJson(response.body?.string(), StringResponse::class.java)
    return Result.success(result)
}

fun executeCreationRequest(request: Request): Result<CreationResponse> {
    val response = executeRequest(request)
        ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

    if (response.code != 200) {
        val exception: ApiException =
            gson.fromJson(response.body?.string(), ApiException::class.java)
        return Result.failure(IOException(response.body?.string() + " " + exception.description + " " + exception.httpStatus))
    }

    val result = gson.fromJson(response.body?.string(), CreationResponse::class.java)
    return Result.success(result)
}