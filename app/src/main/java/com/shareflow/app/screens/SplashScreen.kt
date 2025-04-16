package com.shareflow.app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.shareflow.app.R

@Composable
fun SplashScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                navController.navigate("loginRegisterScreen")
            }
    ) {
        // Fondo
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Splash Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Textos encima del fondo
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Make\nshared living\neasier",
                fontSize = 42.sp,
                color = Color(0xFF2D2D2D),
                fontWeight = FontWeight.Bold,
                lineHeight = 48.sp
            )
        }

        // Texto inferior
        Text(
            text = "Toca para continuar",
            fontSize = 16.sp,
            color = Color(0xFF2D2D2D),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 36.dp),
            textAlign = TextAlign.Center
        )
    }
}
