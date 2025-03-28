package com.shareflow.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shareflow.app.InitialScreen
import com.shareflow.app.LoginScreen
import com.shareflow.app.SignUpScreen


@Composable
fun NavigationGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "initial") {
        composable("initial") {
            InitialScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("signup") {
            SignUpScreen(navController)
        }
    }
}
