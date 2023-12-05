package login

import CoroutineTestRule
import com.basic.template.compose.login.data.repo.LoginRepoImpl
import com.basic.template.compose.login.domain.repo.LoginRepo
import com.basic.template.compose.login.domain.usecases.LoginUseCases
import com.basic.template.compose.login.ui.LoginViewModel
import com.basic.template.network.model.NetworkResponse
import fake.FakeLoginDataSource
import fake.TestDataUtil
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()
    private lateinit var loginViewModel: LoginViewModel

    @Test
    fun testLoginSuccess() {
        val loginDataSource = FakeLoginDataSource(isApiSuccess = true)
        val loginRepo = LoginRepoImpl(loginDataSource)
        val loginUseCases = LoginUseCases(loginRepo)
        loginViewModel = LoginViewModel(loginUseCases)

        // Act
        val expectedData = TestDataUtil.getLoginSuccessResponse() as NetworkResponse.Success

        loginViewModel.loginApiViewModel()
        coroutineTestRule.testDispatcher.scheduler.runCurrent()

        val actualData = loginViewModel.uiState.value as NetworkResponse.Success
        assert(actualData.data?.token == expectedData.data?.token)

    }

    @Test
    fun testLoginFailure() {
        val loginDataSource = FakeLoginDataSource(isApiSuccess = false)
        val loginRepo = LoginRepoImpl(loginDataSource)
        val loginUseCases = LoginUseCases(loginRepo)
        loginViewModel = LoginViewModel(loginUseCases)

        // Act
        val expectedData = TestDataUtil.getFailureResponse()
        loginViewModel.loginApiViewModel()
        coroutineTestRule.testDispatcher.scheduler.runCurrent()

        val actualData = loginViewModel.uiState.value as NetworkResponse.Failure
        assert(expectedData.errorMessage == actualData.errorMessage)
    }

}