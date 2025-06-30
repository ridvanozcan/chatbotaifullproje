package com.ridvanozcan.chatbotaibootcamp.di

import com.ridvanozcan.chatbotaibootcamp.model.ChatGptImageRequestModel
import com.ridvanozcan.chatbotaibootcamp.model.ChatGptImageResponseModel
import com.ridvanozcan.chatbotaibootcamp.model.ChatGptRequestModel
import com.ridvanozcan.chatbotaibootcamp.model.ChatGptResponseModel
import retrofit2.Response
import javax.inject.Inject


class ChatGptRepository @Inject constructor(private val apiServices: ApiServices) {

    suspend fun createMessage(apiKey:String, requestBody: ChatGptRequestModel): Response<ChatGptResponseModel> = apiServices.crateMessage(apiKey, requestBody)
    suspend fun createImage(apiKey:String, requestBody: ChatGptImageRequestModel): Response<ChatGptImageResponseModel> = apiServices.createImage(apiKey, requestBody)
}