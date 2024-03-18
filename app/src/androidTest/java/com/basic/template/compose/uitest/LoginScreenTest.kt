package com.basic.template.compose.uitest

import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import com.basic.template.compose.MyAppNavHost
import com.basic.template.compose.R
import com.basic.template.compose.util.CommonTestUtil.performButton
import com.basic.template.compose.util.CommonTestUtil.performInput
import com.basic.template.compose.util.CommonTestUtil.viewDisplayed
import com.basic.template.compose.util.CommonTestUtil.viewDisplayedUntilWait
import com.basic.template.compose.hilt.AppModule
import com.basic.template.compose.hilt.NetworkModule
import com.basic.template.compose.mock.MockWebServerDispatcher
import com.basic.template.compose.ui.theme.BasicTemplateComposeTheme
import com.basic.template.compose.util.CommonTestUtil
import com.basic.template.compose.util.TestUITag
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class, NetworkModule::class)
class LoginScreenTest : BaseScreenTest() {

    @Test
    fun testLoginFieldsTextInput() {
        mockWebServer.dispatcher = MockWebServerDispatcher().RequestDispatcher()
        CommonTestUtil.initializeComposeTestRule(composeTestRule)
        launchLoginScreenNavGraph()

        viewDisplayedUntilWait(TestUITag.SPLASH_IMAGE, waitSeconds = 12000)
        viewDisplayedUntilWait(
            TestUITag.EMAIL_FIELD_TAG, waitSeconds = 12000
        )
        viewDisplayedUntilWait(
            TestUITag.PASSWORD_FILED_TAG, waitSeconds = 12000
        )

        performInput(
            TestUITag.EMAIL_FIELD_TAG,
            composeTestRule.activity.resources.getString(R.string.test_user_email)
        )
        performInput(
            TestUITag.PASSWORD_FILED_TAG,
            composeTestRule.activity.resources.getString(R.string.test_user_password)
        )

        viewDisplayed(
            TestUITag.EMAIL_FIELD_TAG,
            composeTestRule.activity.resources.getString(R.string.test_user_email)
        )
        viewDisplayed(
            TestUITag.PASSWORD_FILED_TAG,
            composeTestRule.activity.resources.getString(R.string.test_user_password)
        )

        viewDisplayedUntilWait(
            TestUITag.LOGIN_BUTTON_TAG, waitSeconds = 12000
        )

        performButton(TestUITag.LOGIN_BUTTON_TAG)
        viewDisplayedUntilWait(TestUITag.USER_LIST_TITLE, waitSeconds = 12000)
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