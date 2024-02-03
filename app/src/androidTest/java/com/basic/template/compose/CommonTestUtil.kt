package com.basic.template.compose

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.rules.ActivityScenarioRule

object CommonTestUtil {
    private lateinit var composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>

    fun initializeComposeTestRule(composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>) {
        this.composeTestRule = composeTestRule
    }

    fun viewDisplayed(tag: String, validatedString: String) {
        composeTestRule.onNodeWithTag(tag, useUnmergedTree = true)
            .assertTextEquals(validatedString)
    }

    fun performInput(tag: String, inputString: String) {
        composeTestRule.onNodeWithTag(tag).performTextInput(inputString)
    }

    fun performButton(tag: String) {
        composeTestRule.onNodeWithTag(tag).performClick()
    }

    fun performClickOnListItem(tag: String){
        composeTestRule.onNodeWithText(tag).performClick()
    }

    @OptIn(ExperimentalTestApi::class)
    fun viewDisplayedUntilWait(tag: String, waitSeconds: Long = 6000) {
        composeTestRule.waitUntilAtLeastOneExists(
            hasTestTag(tag),
            timeoutMillis = waitSeconds
        )
    }

}