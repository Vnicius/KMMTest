package io.vnicius.github.kmmtest

import io.vnicius.github.kmmtest.api.rest.Greeting
import org.junit.Assert.assertTrue
import org.junit.Test

class AndroidGreetingTest {

    @Test
    fun testExample() {
        assertTrue("Check Android is mentioned", Greeting().greeting().contains("Android"))
    }
}
