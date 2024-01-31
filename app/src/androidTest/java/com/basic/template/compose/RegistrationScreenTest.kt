package com.basic.template.compose

import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.basic.template.compose.CommonTestUtil.performButton
import com.basic.template.compose.CommonTestUtil.performInput
import com.basic.template.compose.CommonTestUtil.viewDisplayedUntilWait
import com.basic.template.compose.hilt.AppModule
import com.basic.template.compose.hilt.NetworkModule
import com.basic.template.compose.ui.theme.BasicTemplateComposeTheme
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
class RegistrationScreenTest {

    @get:Rule(order = 0)
    val hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity> = createAndroidComposeRule<MainActivity>()

    @get:Rule
    val rule = DetectLeaksAfterTestSuccess()

    @Before
    fun setUp() {
        hiltTestRule.inject()
        launchLoginScreenNavGraph()
        CommonTestUtil.initializeComposeTestRule(composeTestRule)
    }

    @After
    fun stop() {
        LeakAssertions.assertNoLeaks()
    }

    @Test
    fun testRegiFieldsTextInput() {
        validateSplashScreen()
        validateLoginScreen()
        validateTextFieldsForRegi()
    }

    private fun validateSplashScreen(){
        viewDisplayedUntilWait(TestUITag.SPLASH_IMAGE)
    }

    private fun validateLoginScreen(){
        viewDisplayedUntilWait(
            TestUITag.EMAIL_FIELD_TAG, waitSeconds = 8000
        )
        viewDisplayedUntilWait(
            TestUITag.PASSWORD_FILED_TAG
        )

        viewDisplayedUntilWait(
            TestUITag.LOGIN_BUTTON_TAG
        )

        viewDisplayedUntilWait(
            TestUITag.DO_NOT_HAVE_ACCOUNT_TAG
        )

        performButton(TestUITag.DO_NOT_HAVE_ACCOUNT_TAG)
    }

    private fun validateTextFieldsForRegi(){
        viewDisplayedUntilWait(
            TestUITag.EMAIL_FIELD_TAG
        )
        viewDisplayedUntilWait(
            TestUITag.PASSWORD_FILED_TAG
        )
        viewDisplayedUntilWait(
            TestUITag.CONFIRM_PASSWORD_FILED_TAG
        )

        performTextFieldsForRegi()
    }

    private fun performTextFieldsForRegi(){
        performInput(TestUITag.EMAIL_FIELD_TAG, composeTestRule.activity.resources.getString(R.string.test_user_email))
        performInput(TestUITag.PASSWORD_FILED_TAG, composeTestRule.activity.resources.getString(R.string.test_user_password))
        performInput(TestUITag.CONFIRM_PASSWORD_FILED_TAG, composeTestRule.activity.resources.getString(R.string.test_user_password))
        performButton(TestUITag.REGISTER_BUTTON_TAG)
        viewDisplayedUntilWait(TestUITag.PROGRESS_BAR)
        viewDisplayedUntilWait(TestUITag.USER_LIST_TITLE)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun launchLoginScreenNavGraph() {
        composeTestRule.activity.setContent {
            BasicTemplateComposeTheme {
                Surface {
                    MyAppNavHost()
                }
            }
        }
    }
}