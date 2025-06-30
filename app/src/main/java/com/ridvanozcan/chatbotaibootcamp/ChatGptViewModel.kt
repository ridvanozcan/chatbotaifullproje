package com.ridvanozcan.chatbotaibootcamp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ridvanozcan.chatbotaibootcamp.Constants.Companion.API_KEY
import com.ridvanozcan.chatbotaibootcamp.model.ChatGptImageRequestModel
import com.ridvanozcan.chatbotaibootcamp.model.ChatGptRequestModel
import com.ridvanozcan.chatbotaibootcamp.model.Choices
import com.ridvanozcan.chatbotaibootcamp.model.Message
import com.ridvanozcan.chatbotaibootcamp.usecase.ChatGptImageUseCase
import com.ridvanozcan.chatbotaibootcamp.usecase.ChatGptUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatGptViewModel @Inject constructor(
    private val useCase: ChatGptUseCase,
    private val imageUseCase: ChatGptImageUseCase
) : ViewModel() {

    private val _generatedMessage = mutableStateOf<List<String>>(emptyList())
    val generatedMessage: State<List<String>> = _generatedMessage


    fun createMessage(text: String) {

        addMessage("Rıdvan: $text")

        viewModelScope.launch {
            val response = useCase.invoke(
                apiKey = "Bearer $API_KEY",
                requestBody = ChatGptRequestModel(
                    model = "gpt-4o-mini",
                    messages = listOf(
                        Message(
                            role = "system",
                            content = "You are a helpful assistant"
                        ),
                        Message(
                            role = "user",
                            content = text
                        )
                    ),
                    maxTokens = 2500
                )
            )

            if (response.isSuccessful) {
                response.body()?.let {
                    addMessage("ChatBot Message : " + it.choices.first().message.content)
                }
            }
        }
    }

    private fun addMessage(message: String) {
        _generatedMessage.value += message
    }

    fun createImage(text: String) {
        addMessage("Rıdvan Resim: $text")
        viewModelScope.launch {
            val response = imageUseCase.invoke(
                apiKey = "Bearer $API_KEY",
                requestBody = ChatGptImageRequestModel(
                    model = "dall-e-3",
                    prompt = text,
                    n = 1,
                    size = "1024x1024"
                )
            )

            if (response.isSuccessful) {
                response.body()?.let {
                    addMessage(it.data.first().url)
                }
            }
        }
    }
}