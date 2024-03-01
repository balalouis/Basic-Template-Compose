package com.login

import com.basic.template.network.model.NetworkResponse
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()
    private lateinit var viewModel: LoginViewModel

    @Test
    fun testLoginSuccess() {
        val dataSource = FakeLoginDataSource(isApiSuccess = true)
        val repo = LoginRepoImpl(dataSource)
        val useCases = LoginUseCases(repo)
        viewModel = LoginViewModel(useCases)

        // Act
        val expectedData = TestDataUtil.getLoginSuccessResponse() as NetworkResponse.Success

        viewModel.loginApiViewModel()
        coroutineTestRule.testDispatcher.scheduler.runCurrent()

        val actualData = viewModel.uiState.value as NetworkResponse.Success
        assert(actualData.data?.token == expectedData.data?.token)

    }

    @Test
    fun testLoginFailure() {
        val dataSource = FakeLoginDataSource(isApiSuccess = false)
        val repo = LoginRepoImpl(dataSource)
        val useCases = LoginUseCases(repo)
        viewModel = LoginViewModel(useCases)

        // Act
        val expectedData = TestDataUtil.getFailureResponse()
        viewModel.loginApiViewModel()
        coroutineTestRule.testDispatcher.scheduler.runCurrent()

        val actualData = viewModel.uiState.value as NetworkResponse.Failure
        assert(expectedData.errorMessage == actualData.errorMessage)
    }

}