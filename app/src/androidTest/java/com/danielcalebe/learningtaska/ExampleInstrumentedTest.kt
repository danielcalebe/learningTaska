package com.danielcalebe.learningtaska

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import java.io.File

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
  @get:Rule
  val composeTestRule = createAndroidComposeRule<MainActivity>()

  fun d() {
    Thread.sleep(0)
  }

  //acessar aplicativo
  @Test
  fun `1`() {
    d()
    composeTestRule.onNodeWithText("HOME").assertIsDisplayed()
    d()
    composeTestRule.onNodeWithText("Cadastrar \natividade").assertIsDisplayed()
    d()
    composeTestRule.onNodeWithText("Ver \nKanban").assertIsDisplayed()
    d()
    composeTestRule.onNodeWithText("Gerar \nrelatório").assertIsDisplayed()
    d()
  }


  //Navegar para cadastro
  @Test
  fun `2`() {
    d()
    composeTestRule.onNodeWithText("Cadastrar \natividade").performClick()
    d()
    composeTestRule.waitForIdle()
    composeTestRule.onNodeWithText("CADASTRAR ATIVIDADE").assertIsDisplayed()
    d()
    composeTestRule.onNodeWithText("Título da tarefa").assertIsDisplayed()
    d()
  }


  @Test
  fun `3`() {
    d()
    composeTestRule.onNodeWithText("Cadastrar \natividade").performClick()
    d()
    composeTestRule.onNodeWithText("Cadastrar").performClick()
    d()
    composeTestRule.onNodeWithText("Esse campo é obrigatório").assertIsDisplayed()
    d()
  }


  @Test
  fun `4`() {
    d()
    composeTestRule.onNodeWithText("Cadastrar \natividade").performClick()
    composeTestRule.waitForIdle()
    d()
    composeTestRule.onNodeWithText("Título da tarefa").performTextInput("Atividade 01")
    composeTestRule.waitForIdle()
    d()
    composeTestRule.onNodeWithText("Cadastrar").performClick()
    composeTestRule.waitForIdle()
    d()
    d()
  }


  @Test
  fun `5`() {
    d()
    composeTestRule.onNodeWithText("Ver \nKanban").performClick()
    d()
    composeTestRule.waitForIdle()
    composeTestRule.onNodeWithText("TO DO").assertIsDisplayed()
    composeTestRule.onNodeWithText("DOING").assertIsDisplayed()
    composeTestRule.onNodeWithText("DONE").assertIsDisplayed()
    d()
  }


  @Test
  fun `6`() {
    d()
    composeTestRule.onNodeWithText("Ver \nKanban").performClick()
    d()
    composeTestRule.waitForIdle()
    d()
    composeTestRule.onNodeWithText("Atividade 01").assertIsDisplayed()
    d()
  }

  @Test
  fun `7`() {
    d()
//    `4`()
    composeTestRule.onNodeWithText("Ver \nKanban").performClick()
    d()
    composeTestRule.waitForIdle()
    d()
    composeTestRule.onNodeWithContentDescription("Atividade 01-TODO-NEXT").performClick()
    d()
    composeTestRule.onNode(
      hasText("DOING") and hasAnyChild(hasText("Atividade 01"))
    )
  }

  @Test
  fun `8`() {
    d()
//    `4`()
    composeTestRule.onNodeWithText("Ver \nKanban").performClick()
    d()
    composeTestRule.waitForIdle()
    d()
    composeTestRule.onNodeWithContentDescription("Atividade 01-DOING-NEXT").performClick()
    d()
    composeTestRule.onNode(
      hasText("DONE") and hasAnyChild(hasText("Atividade 01"))
    )
  }

  @Test
  fun `9`() {
    d()
//    `4`()
    composeTestRule.onNodeWithText("Ver \nKanban").performClick()
    d()
    composeTestRule.waitForIdle()
    d()
    composeTestRule.onNodeWithContentDescription("Atividade 01-DONE-NEXT").performClick()

  }


  @Test
  fun `10`() {
    d()
//    `4`()
    composeTestRule.onNodeWithText("Ver \nKanban").performClick()
    d()
    composeTestRule.waitForIdle()
    d()
    composeTestRule.onNodeWithContentDescription("Atividade 01-DONE-PREVIOUS").performClick()
    d()
    composeTestRule.onNode(
      hasText("DOING") and hasAnyChild(hasText("Atividade 01"))
    )

  }


  @Test
  fun `11`() {
    d()
//    `4`()
    composeTestRule.onNodeWithText("Ver \nKanban").performClick()
    d()
    composeTestRule.waitForIdle()
    d()
    composeTestRule.onNodeWithContentDescription("Atividade 01-DOING-PREVIOUS").performClick()
    d()
    composeTestRule.onNode(
      hasText("TO DO") and hasAnyChild(hasText("Atividade 01"))
    )

  }


  @Test
  fun `12`() {
    d()
//    `4`()
    composeTestRule.onNodeWithText("Ver \nKanban").performClick()
    d()
    composeTestRule.waitForIdle()
    d()
    composeTestRule.onNodeWithContentDescription("Atividade 01-TODO-PREVIOUS").performClick()
    d()
    composeTestRule.onNode(
      hasText("TO DO") and hasAnyChild(hasText("Atividade 01"))
    )

  }

  @Test
  fun `13`() {
    d()
    composeTestRule.onNodeWithText("Cadastrar \natividade").performClick()
    composeTestRule.waitForIdle()
    d()
    composeTestRule.onNodeWithText("Título da tarefa").performTextInput("Atividade 02")
    composeTestRule.waitForIdle()
    d()
    composeTestRule.onNodeWithText("Cadastrar").performClick()
    composeTestRule.waitForIdle()
    d()
    d()

    d()
    composeTestRule.onNodeWithText("Cadastrar\natividade").performClick()
    composeTestRule.waitForIdle()
    d()
    composeTestRule.onNodeWithText("Título da tarefa").performTextInput("Atividade 03")
    composeTestRule.waitForIdle()
    d()
    composeTestRule.onNodeWithText("Cadastrar").performClick()
    composeTestRule.waitForIdle()
    d()
  }

  @Test
  fun `14`() {
    d()
    composeTestRule.onNodeWithText("Ver \nKanban").performClick()
    d()
    composeTestRule.waitForIdle()
    d()
    composeTestRule.onNodeWithContentDescription("Atividade 02-TODO-NEXT").performClick()
    d()

    composeTestRule.onNodeWithContentDescription("Atividade 03-TODO-NEXT").performClick()
    d()
    composeTestRule.onNodeWithContentDescription("Atividade 03-DOING-NEXT").performClick()
  }


  @Test
  fun `15`() {
    d()
    composeTestRule.onNodeWithText("Gerar \nrelatório").performClick()
    d()
  }


  @Test
  fun `16`() {
    d()
    composeTestRule.onNodeWithText("ERROR").assertIsDisplayed()
  }

  @Test
  fun `17`() {
    d()
    composeTestRule.onNodeWithText("Ver \nKanban").performClick()
    d()
    composeTestRule.onNodeWithText("Atividade 01").assertIsDisplayed()
    composeTestRule.onNodeWithText("Atividade 02").assertIsDisplayed()
    composeTestRule.onNodeWithText("Atividade 03").assertIsDisplayed()

    composeTestRule.activity.finish()
    composeTestRule.activityRule.scenario.recreate()

    composeTestRule.onNodeWithText("Ver \nKanban").performClick()
    d()
    composeTestRule.onNodeWithText("Atividade 01").assertIsDisplayed()
    composeTestRule.onNodeWithText("Atividade 02").assertIsDisplayed()
    composeTestRule.onNodeWithText("Atividade 03").assertIsDisplayed()
    d()

  }

}