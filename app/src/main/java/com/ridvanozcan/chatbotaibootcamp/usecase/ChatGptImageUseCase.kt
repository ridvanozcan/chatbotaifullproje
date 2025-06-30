package com.ridvanozcan.chatbotaibootcamp.usecase

import com.ridvanozcan.chatbotaibootcamp.di.ChatGptRepository
import com.ridvanozcan.chatbotaibootcamp.model.ChatGptImageRequestModel
import com.ridvanozcan.chatbotaibootcamp.model.ChatGptImageResponseModel
import com.ridvanozcan.chatbotaibootcamp.model.ChatGptRequestModel
import com.ridvanozcan.chatbotaibootcamp.model.ChatGptResponseModel
import retrofit2.Response
import javax.inject.Inject

class ChatGptImageUseCase @Inject constructor(private val repository: ChatGptRepository) {

    suspend operator fun invoke(
        apiKey: String,
        requestBody: ChatGptImageRequestModel
    ): Response<ChatGptImageResponseModel> = repository.createImage(apiKey, requestBody)
}