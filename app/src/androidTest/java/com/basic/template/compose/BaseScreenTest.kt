package com.basic.template.compose

import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.basic.template.compose.CommonTestUtil.performButton
import com.basic.template.compose.CommonTestUtil.performInput
import com.basic.template.compose.CommonTestUtil.viewDisplayed
import com.basic.template.compose.CommonTestUtil.viewDisplayedUntilWait
import com.basic.template.compose.hilt.AppModule
import com.basic.template.compose.hilt.NetworkModule
import com.basic.template.compose.mock.MockWebServerDispatcher
import com.basic.template.compose.ui.theme.BasicTemplateComposeTheme
import com.basic.template.compose.util.TestUITag
import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import leakcanary.DetectLeaksAfterTestSuccess
import leakcanary.LeakAssertions
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(AppModule::class, NetworkModule::class)
open class BaseScreenTest {

    @get:Rule(order = 0)
    val hiltTestRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    val rule = DetectLeaksAfterTestSuccess()

    @Inject
    lateinit var okHttp: OkHttpClient

    val mockWebServer = MockWebServer()
    private lateinit var okHttp3IdlingResource: OkHttp3IdlingResource

    @Before
    fun setUp() {
        hiltTestRule.inject()
        okHttp3IdlingResource = OkHttp3IdlingResource.create("okhttp", okHttp)
        IdlingRegistry.getInstance().register(okHttp3IdlingResource)
        mockWebServer.start(8080)
    }

    @After
    fun stop() {
        LeakAssertions.assertNoLeaks()
        mockWebServer.shutdown()
        IdlingRegistry.getInstance().unregister(okHttp3IdlingResource)
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