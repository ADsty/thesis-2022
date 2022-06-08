package com.petrov.vitaliy.caraccidentapp.data.repository

import com.petrov.vitaliy.caraccidentapp.data.*
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.chats.MessageCreationRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.chats.MessageUpdateRequest
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.chats.ChatGetResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.chats.MessageGetResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.ApiException
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.CreationResponse
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.general.StringResponse
import com.petrov.vitaliy.caraccidentapp.domain.repository.ChatRepository
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class ChatRepositoryImpl : ChatRepository {

    override fun getAllUserChats(jwtToken: String): Result<Array<ChatGetResponse>> {

        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("chats")
            .addPathSegment("get-all-user")
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeChatsRequest(request)

    }

    override fun getAllOfficerChats(jwtToken: String): Result<Array<ChatGetResponse>> {

        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("chats")
            .addPathSegment("get-all-officer")
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeChatsRequest(request)

    }

    override fun getAllChatMessages(
        jwtToken: String,
        chatID: Long
    ): Result<Array<MessageGetResponse>> {

        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("chats")
            .addPathSegment("messages")
            .addPathSegment("get-all")
            .addQueryParameter("chatID", chatID.toString())
            .build()

        val request = Request.Builder().get().url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeMessagesRequest(request)

    }

    override fun sendMessage(
        jwtToken: String,
        messageCreationRequest: MessageCreationRequest
    ): Result<CreationResponse> {

        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("chats")
            .addPathSegment("messages")
            .addPathSegment("send")
            .addQueryParameter("addressee", messageCreationRequest.addressee.toString())
            .addQueryParameter(
                "messageCreationDate",
                messageCreationRequest.messageCreationDate.toString()
            )
            .addQueryParameter(
                "messageCreationTime",
                messageCreationRequest.messageCreationTime.toString()
            )
            .addQueryParameter("messageText", messageCreationRequest.messageText)
            .addQueryParameter("chatID", messageCreationRequest.chatID.toString())
            .build()

        val request = Request.Builder().post(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeCreationRequest(request)

    }

    override fun updateMessage(
        jwtToken: String,
        messageUpdateRequest: MessageUpdateRequest
    ): Result<StringResponse> {

        val byte = byteArrayOf()
        val body: RequestBody = byte.toRequestBody(null, 0)


        val httpBuilder: HttpUrl = HttpUrl.Builder()
            .scheme(scheme)
            .host(host)
            .port(port)
            .addPathSegment("chats")
            .addPathSegment("messages")
            .addPathSegment("update")
            .addQueryParameter(
                "messageUpdateDate",
                messageUpdateRequest.messageUpdateDate.toString()
            )
            .addQueryParameter(
                "messageUpdateTime",
                messageUpdateRequest.messageUpdateTime.toString()
            )
            .addQueryParameter("updatedMessageText", messageUpdateRequest.updatedMessageText)
            .addQueryParameter("messageID", messageUpdateRequest.messageID.toString())
            .build()

        val request = Request.Builder().patch(body).url(httpBuilder).apply {
            addHeader("Authorization", "Bearer $jwtToken")
        }.build()

        return executeStringRequest(request)

    }

    private fun executeChatsRequest(request: Request): Result<Array<ChatGetResponse>> {
        val response = executeRequest(request)
            ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

        if (response.code != 200) {
            val exception: ApiException =
                gson.fromJson(response.body?.string(), ApiException::class.java)
            return Result.failure(IOException(response.body?.string() + " " + exception.description + " " + exception.httpStatus))
        }

        val result = gson.fromJson(response.body?.string(), Array<ChatGetResponse>::class.java)
        return Result.success(result)
    }

    private fun executeMessagesRequest(request: Request): Result<Array<MessageGetResponse>> {
        val response = executeRequest(request)
            ?: return Result.failure(IOException("Не удалось обратиться к серверу"))

        if (response.code != 200) {
            val exception: ApiException =
                gson.fromJson(response.body?.string(), ApiException::class.java)
            return Result.failure(IOException(response.body?.string() + " " + exception.description + " " + exception.httpStatus))
        }

        val result = gson.fromJson(response.body?.string(), Array<MessageGetResponse>::class.java)
        return Result.success(result)
    }

}