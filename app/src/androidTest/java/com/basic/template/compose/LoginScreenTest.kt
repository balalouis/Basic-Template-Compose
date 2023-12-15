package com.basic.template.compose

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.basic.template.compose.hilt.AppModule
import com.basic.template.compose.hilt.NetworkModule
import com.basic.template.compose.login.ui.LoginScreen
import com.basic.template.compose.login.ui.LoginViewModel
import com.basic.template.compose.screen.LoginScreen
import com.basic.template.compose.screen.RegisterScreen
import com.basic.template.compose.screen.UserListScreen
import com.basic.template.compose.ui.theme.BasicTemplateComposeTheme
import com.basic.template.compose.userlist.ui.UserListScreen
import com.basic.template.compose.userlist.ui.UserListViewModel
import com.basic.template.compose.util.TestUITag
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import leakcanary.DetectLeaksAfterTestSuccess
import leakcanary.LeakAssertions
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class, NetworkModule::class)
class LoginScreenTest {

    @get:Rule(order = 0)
    val hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    val rule = DetectLeaksAfterTestSuccess()

    private lateinit var navController: NavController

    @Before
    fun setUp() {
        hiltTestRule.inject()
        launchLoginScreenNavGraph()
    }

    @After
    fun stop() {
        LeakAssertions.assertNoLeaks()
    }

    @Test
    fun testSampleUiViews() {
        composeTestRule.onNodeWithText("Email").assertIsDisplayed()
        composeTestRule.onNodeWithText("Password").assertIsDisplayed()
    }

    @Test
    fun testLoginUI() {
        loginUIValidation()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun testLoginFlowViaUntilAdditional() {
        typeUserInput()
        composeTestRule.onNodeWithTag(TestUITag.LOGIN_BUTTON_TAG).performClick()
        composeTestRule.waitUntilDoesNotExist(
            hasTestTag(TestUITag.PROGRESS_BAR),
            timeoutMillis = 6000
        )
        composeTestRule.waitUntilAtLeastOneExists(
            hasTestTag(TestUITag.USER_LIST_TITLE),
            timeoutMillis = 6000
        )
    }

    private fun typeUserInput() {
        // Typing the email and password
        composeTestRule.onNodeWithTag(TestUITag.EMAIL_FIELD_TAG)
            .performTextInput(composeTestRule.activity.resources.getString(R.string.test_user_email))
        composeTestRule.onNodeWithTag(TestUITag.PASSWORD_FILED_TAG)
            .performTextInput(composeTestRule.activity.resources.getString(R.string.test_user_password))
    }

    private fun loginUIValidation() {
        // UI is visible
        composeTestRule.onNodeWithTag(TestUITag.EMAIL_TAG, useUnmergedTree = true)
            .assertTextEquals(composeTestRule.activity.resources.getString(R.string.email))
        composeTestRule.onNodeWithTag(TestUITag.PASSWORD_TAG, useUnmergedTree = true)
            .assertTextEquals(composeTestRule.activity.resources.getString(R.string.password))
        composeTestRule
            .onNodeWithTag(TestUITag.LOGIN_BUTTON_TAG)
            .assertTextEquals(composeTestRule.activity.resources.getString(R.string.login))

        typeUserInput()

        // Validating the UI after entering the text
        composeTestRule.onNodeWithTag(TestUITag.EMAIL_FIELD_TAG, useUnmergedTree = true)
            .assertTextEquals(composeTestRule.activity.resources.getString(R.string.email))
        composeTestRule.onNodeWithTag(TestUITag.PASSWORD_FILED_TAG, useUnmergedTree = true)
            .assertTextEquals(composeTestRule.activity.resources.getString(R.string.test_user_password))
    }

    private fun launchLoginScreenNavGraph() {
        composeTestRule.activity.setContent {
            BasicTemplateComposeTheme {
                navController = rememberNavController()
                NavHost(
                    navController = navController as NavHostController,
                    startDestination = LoginScreen.route
                ) {

                    composable(LoginScreen.route) {
                        val userName = remember {
                            mutableStateOf(TextFieldValue(""))
                        }
                        val password = remember {
                            mutableStateOf(TextFieldValue(""))
                        }
                        val loginViewModelObj: LoginViewModel = hiltViewModel()

                        LoginScreen(
                            navController,
                            onNavigateToRegister = { navController.navigate(RegisterScreen.route) },
                            userName = userName,
                            password = password,
                            loginViewModel = loginViewModelObj
                        )

                    }

                    composable(UserListScreen.route) {
                        val userListViewModel: UserListViewModel = hiltViewModel()
                        UserListScreen(
                            navController,
                            userListViewModel,
                            paddingValues = PaddingValues()
                        )
                    }
                }
            }
        }
    }
}