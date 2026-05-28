package com.danielcalebe.learningtaska

import android.content.Context
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.io.File

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
        val composeTestRule = createAndroidComposeRule<MainActivity>()
//    @Test
//    fun generateFile_shouldCreateTxtFile() {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//
//        val file = File(context.filesDir, "report.txt")
//
//        file.writeText("teste")
//
//        assertTrue(file.exists())
//
//        val content = file.readText()
//
//        assertEquals("teste", content)
//    }
}