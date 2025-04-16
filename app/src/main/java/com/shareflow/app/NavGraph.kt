package com.shareflow.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shareflow.app.screens.SplashScreen
import com.shareflow.app.screens.LoginRegisterScreen


@Composable
fun NavigationGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("loginRegisterScreen") {
            LoginRegisterScreen(navController)
        }




    }
}
