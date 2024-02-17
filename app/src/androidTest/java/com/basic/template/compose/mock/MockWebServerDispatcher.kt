package com.basic.template.compose.mock

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.util.concurrent.TimeUnit

class MockWebServerDispatcher {

    internal inner class RequestDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "/api/users?page=2" ->
                    MockResponse().setResponseCode(200)
                        .setBody(FileReader.readStringFromFile("user_list_success_response.json"))
                        .throttleBody(1024, 200L, TimeUnit.MILLISECONDS)
                "/api/login" ->
                    MockResponse().setResponseCode(200)
                        .setBody(FileReader.readStringFromFile("login_success_response.json"))
                        .throttleBody(1024, 200L, TimeUnit.MILLISECONDS)
                "/api/register" ->
                    MockResponse().setResponseCode(200)
                        .setBody(FileReader.readStringFromFile("registration_success_response.json"))
                        .throttleBody(1024, 200L, TimeUnit.MILLISECONDS)
                else -> MockResponse().setResponseCode(400)
            }
        }
    }

    internal inner class ErrorDispatcher : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return MockResponse().setResponseCode(400)
                .setBody(Constants.ERROR_MESSAGE)
        }
    }
}