package com.basic.template.compose

import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.basic.template.compose.CommonTestUtil.performButton
import com.basic.template.compose.CommonTestUtil.performInput
import com.basic.template.compose.CommonTestUtil.performClickOnListItem
import com.basic.template.compose.CommonTestUtil.viewDisplayed
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
class UserScreenTest {

    @get:Rule(order = 0)
    val hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    val rule = DetectLeaksAfterTestSuccess()

    @Before
    fun setUp() {
        hiltTestRule.inject()
        CommonTestUtil.initializeComposeTestRule(composeTestRule)
        launchLoginScreenNavGraph()
    }

    @After
    fun stop() {
        LeakAssertions.assertNoLeaks()
    }

    @Test
    fun testLoginFieldsTextInput() {
        viewDisplayedUntilWait(TestUITag.SPLASH_IMAGE)
        viewDisplayedUntilWait(
            TestUITag.EMAIL_FIELD_TAG
        )
        viewDisplayedUntilWait(
            TestUITag.PASSWORD_FILED_TAG
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
            TestUITag.LOGIN_BUTTON_TAG
        )

        performButton(TestUITag.LOGIN_BUTTON_TAG)
        viewDisplayedUntilWait(TestUITag.PROGRESS_BAR)
        viewDisplayedUntilWait(TestUITag.USER_LIST_TITLE, waitSeconds = 8000)
        performClickOnListItem("Lindsay")
        viewDisplayedUntilWait(TestUITag.USER_DETAIL_TAG)
        viewDisplayed(tag=TestUITag.USER_EMAIL_TAG,"lindsay.ferguson@reqres.in")
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