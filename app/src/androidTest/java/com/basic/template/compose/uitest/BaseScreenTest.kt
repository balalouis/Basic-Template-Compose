package com.basic.template.compose.uitest

import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.espresso.IdlingRegistry
import com.basic.template.compose.MainActivity
import com.basic.template.compose.MyAppNavHost
import com.basic.template.compose.hilt.AppModule
import com.basic.template.compose.hilt.NetworkModule
import com.basic.template.compose.ui.theme.BasicTemplateComposeTheme
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