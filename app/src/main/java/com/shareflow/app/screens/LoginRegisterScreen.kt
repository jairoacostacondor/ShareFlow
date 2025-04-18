package com.shareflow.app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.shareflow.app.R
import androidx.compose.ui.graphics.Color

@Composable
fun LoginRegisterScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Fondo difuminado
        Image(
            painter = painterResource(id = R.drawable.background_blured),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Contenido principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título "Regístrate"
            Text(
                text = "Regístrate",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333),
                textAlign = TextAlign.Center
            )

            // Separador entre "Regístrate" y "o accede"
            Spacer(modifier = Modifier.height(8.dp)) // Aquí decides cuánto espacio quieres

            // Subtítulo "o accede"
            Text(
                text = "o accede",
                fontSize = 36.sp, // Puedes ajustar el tamaño si quieres que se vea un poco más pequeño
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Botón Registrarse
            Button(
                onClick = { navController.navigate("registerScreen") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF8F3EE)
                )
            ) {
                Text(
                    text = "Regístrate",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333),
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Botón Accede
            Button(
                onClick = { navController.navigate("loginScreen") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF8F3EE)
                )
            ) {
                Text(
                    text = "Accede",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333),
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(30.dp))


        }
    }
}

