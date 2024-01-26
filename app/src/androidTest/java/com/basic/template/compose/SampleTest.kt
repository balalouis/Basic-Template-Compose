package com.basic.template.compose

import io.cucumber.junit.CucumberOptions

@CucumberOptions(
    glue = ["com.basic.template.compose"],
    tags = "~@wip",
    features = ["features"]
)
class SampleTest {
}