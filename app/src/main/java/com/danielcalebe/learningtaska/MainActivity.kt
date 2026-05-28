package com.danielcalebe.learningtaska

import android.content.Context
import android.os.Bundle
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

                    fun gerarRelatorio() {
                        val todo = getTasks().filter { it.status == "todo" }.map { it.title }
                        val doing = getTasks().filter { it.status == "doing" }.map { it.title }
                        val done = getTasks().filter { it.status == "done" }.map { it.title }
                        val string = """
                           To do
                            ${todo.forEach { } }
                        """.trimIndent()
                    }
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        NavHost(navController, "kanban") {
                            composable("home") {
                                Home(
                                    gerarRelatorio = {},
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


