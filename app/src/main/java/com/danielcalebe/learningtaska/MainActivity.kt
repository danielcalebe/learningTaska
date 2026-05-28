package com.danielcalebe.learningtaska

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.danielcalebe.learningtaska.ui.theme.LearningTaskaTheme
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.collections.emptyList
import androidx.core.content.edit
import java.io.File
import java.util.UUID


@Serializable
data class Task(
  val title: String,
  val status: String = "todo",
  val uuid: String = UUID.randomUUID().toString(),
)

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      LearningTaskaTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          val navController = rememberNavController()
          val ctx = LocalContext.current
          val sp = ctx.getSharedPreferences("prefs", Context.MODE_PRIVATE)
          fun getTasks(): List<Task> {
            val string = sp.getString("tasks", "") ?: ""
            val taskList = try {
              Json.decodeFromString<List<Task>>(string)
            } catch (e: Exception) {
              e.printStackTrace()
              emptyList()
            }
            return taskList
          }

          fun newTask(new: Task) {
            val taskList = getTasks().toMutableList()
            taskList.add(new)
            sp.edit { putString("tasks", Json.encodeToString(taskList)) }
          }

          fun editTask(editedTask: Task) {
            val tasksList = getTasks().toMutableList()
            tasksList.removeIf { it.uuid == editedTask.uuid }
            tasksList.add(editedTask)
            sp.edit { putString("tasks", Json.encodeToString(tasksList)) }
          }


          Column(
            modifier = Modifier.padding(innerPadding)
          ) {
            NavHost(navController, "home") {
              composable("home") {
                Home(
                  gerarRelatorio = {
                    val a = gerarRelatorio(getTasks())
                    Toast.makeText(
                      ctx, "Arquivo de relatório gerado na pasta Downloads: $a",
                      Toast.LENGTH_LONG
                    ).show()
                  },
                  verKanban = { navController.navigate("kanban") },
                  cadastrar = { navController.navigate("cadastrar") }
                )
              }
              composable("cadastrar") {
                Cadastrar(
                  onBack = { navController.navigate("home") },
                  onRegister = {
                    newTask(Task(it)); Toast.makeText(
                    ctx, "Tarefa cadastrada com sucesso",
                    Toast.LENGTH_LONG
                  ).show(); navController.navigate("kanban")
                  }
                )
              }
              composable("kanban") {
                Kanban(
                  ctx = ctx,
                  onBack = { navController.navigate("home") },
                  cadastrarAividade = { navController.navigate("cadastrar") },
                  getTaskList = { getTasks() },
                  onNext = { task ->
                    when (task.status) {
                      "todo" -> {
                        editTask(task.copy(status = "doing"))
                      }

                      "doing" -> {
                        editTask(task.copy(status = "done"))
                      }

                      "done" -> {

                      }
                    }
                  },
                  onPrevious = { task ->
                    when (task.status) {
                      "todo" -> {
                      }

                      "doing" -> {
                        editTask(task.copy(status = "todo"))
                      }

                      "done" -> {
                        editTask(task.copy(status = "doing"))
                      }
                    }
                  }

                )
              }
            }

          }
        }
      }
    }
  }
}


fun gerarRelatorio(tasks: List<Task>): String {
  val todo = tasks.filter { it.status == "todo" }
  val doing = tasks.filter { it.status == "doing" }
  val done = tasks.filter { it.status == "done" }
  val string = """
    To do
    ${todo.joinToString("\n") { " -${it.title}" }}
    
    Doing
    ${doing.joinToString("\n") { " -${it.title}" }}
    
    Done 
    ${done.joinToString("\n") { " -${it.title}" }}
  """.trimIndent()

  val uuid = UUID.randomUUID().toString()
  val fileName = "relatorio_taska_${System.currentTimeMillis()}.txt"
  val file = File(
    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName
  )
  file.writeText(string)
  return fileName
}


fun gerarRelatorio2(task: List<Task>) {
  val string = """
  To do 
  ${task.joinToString("\n") { "- ${it.title}" }}
""".trimIndent()
  val uuid = UUID.randomUUID().toString()
  val file =
    File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "ola.txt")
  file.writeText(string)

}