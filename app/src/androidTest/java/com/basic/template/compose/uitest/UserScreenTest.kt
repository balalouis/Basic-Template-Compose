package com.basic.template.compose.uitest

import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import com.basic.template.compose.MyAppNavHost
import com.basic.template.compose.R
import com.basic.template.compose.util.CommonTestUtil.performButton
import com.basic.template.compose.util.CommonTestUtil.performInput
import com.basic.template.compose.util.CommonTestUtil.performClickOnListItem
import com.basic.template.compose.util.CommonTestUtil.viewDisplayed
import com.basic.template.compose.util.CommonTestUtil.viewDisplayedUntilWait
import com.basic.template.compose.hilt.AppModule
import com.basic.template.compose.hilt.NetworkModule
import com.basic.template.compose.hilt.UrlModule
import com.basic.template.compose.mock.MockWebServerDispatcher
import com.basic.template.compose.ui.theme.BasicTemplateComposeTheme
import com.basic.template.compose.util.CommonTestUtil
import com.basic.template.compose.util.TestUITag
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class, NetworkModule::class, UrlModule::class)
class UserScreenTest: BaseScreenTest() {

    private fun validateLoginFields() {
        viewDisplayedUntilWait(TestUITag.SPLASH_IMAGE)
        viewDisplayedUntilWait(
            TestUITag.EMAIL_FIELD_TAG, waitSeconds = WAIT_MILLI
        )
        viewDisplayedUntilWait(
            TestUITag.PASSWORD_FILED_TAG, waitSeconds = WAIT_MILLI
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
            TestUITag.LOGIN_BUTTON_TAG, waitSeconds = WAIT_MILLI
        )
        performButton(TestUITag.LOGIN_BUTTON_TAG)
    }

    @Test
    fun validateUserList(){
        mockWebServer.dispatcher = MockWebServerDispatcher().RequestDispatcher()
        CommonTestUtil.initializeComposeTestRule(composeTestRule)
        launchLoginScreenNavGraph()

        validateLoginFields()
        viewDisplayedUntilWait(TestUITag.USER_LIST_TITLE, waitSeconds = WAIT_MILLI)
        performClickOnListItem("Arunkumar")
    }

    @Test
    fun validateUserDetail(){
        validateUserList()
        viewDisplayedUntilWait(TestUITag.USER_DETAIL_TAG, waitSeconds = WAIT_MILLI)
        viewDisplayed(TestUITag.USER_FIRST_NAME_TAG,"Arunkumar")
        viewDisplayed(TestUITag.USER_LAST_NAME_TAG,"Veerannan")
        viewDisplayed(TestUITag.USER_EMAIL_TAG,"arunkumar.veerannan@reqres.in")
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