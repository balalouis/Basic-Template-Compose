package com.example.registration

import com.basic.template.network.model.NetworkResponse
import org.junit.Rule
import org.junit.Test

class RegistrationViewModelTest {
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()
    private lateinit var viewModel: RegistrationViewModel

    @Test
    fun testRegistrationSuccess() {
        val dataSource = FakeRegistrationDataSource(isApiSuccess = true)
        val repo = RegistrationRepoImpl(dataSource)
        val useCases = RegistrationUseCases(repo)
        viewModel = RegistrationViewModel(useCases)

        // Act
        val expectedData = TestDataUtil.getRegistrationSuccessResponse() as NetworkResponse.Success

        viewModel.registrationViaApiViewModel()
        coroutineTestRule.testDispatcher.scheduler.runCurrent()

        val actualData = viewModel.uiState.value as NetworkResponse.Success
        assert(actualData.data?.token == expectedData.data?.token)

    }

    @Test
    fun testRegistrationFailure() {
        val dataSource = FakeRegistrationDataSource(isApiSuccess = false)
        val repo = RegistrationRepoImpl(dataSource)
        val useCases = RegistrationUseCases(repo)
        viewModel = RegistrationViewModel(useCases)

        // Act
        val expectedData = TestDataUtil.getFailureResponse()
        viewModel.registrationViaApiViewModel()
        coroutineTestRule.testDispatcher.scheduler.runCurrent()

        val actualData = viewModel.uiState.value as NetworkResponse.Failure
        assert(expectedData.errorMessage == actualData.errorMessage)
    }

}