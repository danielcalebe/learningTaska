package com.danielcalebe.learningtaska

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.danielcalebe.learningtaska.ui.theme.LearningTaskaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearningTaskaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        NavHost(navController, "home") {
                            composable("home") {
                                Home(
                                    gerarRelatorio = {},
                                    verKanban = { navController.navigate("kanban") },
                                    cadastrar = { navController.navigate("cadastrar") }
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}