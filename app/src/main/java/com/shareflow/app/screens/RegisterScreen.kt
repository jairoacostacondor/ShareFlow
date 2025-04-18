package com.shareflow.app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.shareflow.app.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.shareflow.app.models.User
import com.shareflow.app.network.RetrofitInstance


@Composable
fun RegisterScreen(navController: NavHostController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Fondo
        Image(
            painter = painterResource(id = R.drawable.background_blured),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Contenido
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título
            Text(
                text = "Crear cuenta",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Campo Nombre
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text(text = "Nombre", color = Color(0xFF9E9E9E)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF8F3EE),
                    unfocusedContainerColor = Color(0xFFF8F3EE),
                    disabledContainerColor = Color(0xFFF8F3EE),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent
                )
                ,
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

// Campo Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text(text = "Email", color = Color(0xFF9E9E9E)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF8F3EE),
                    unfocusedContainerColor = Color(0xFFF8F3EE),
                    disabledContainerColor = Color(0xFFF8F3EE),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent
                )
                ,
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

// Campo Contraseña
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text(text = "Contraseña", color = Color(0xFF9E9E9E)) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF8F3EE),
                    unfocusedContainerColor = Color(0xFFF8F3EE),
                    disabledContainerColor = Color(0xFFF8F3EE),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent
                )
                ,
                singleLine = true
            )


            Spacer(modifier = Modifier.height(24.dp))

            // Botón Registrarse
            Button(
                onClick = {
                    // Crea el objeto User
                    val user = User(
                        nombre = name,
                        email = email,
                        contrasena = password
                    )

                    // Ejecuta la llamada a la API
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val response = RetrofitInstance.api.registerUser(user)
                            if (response.isSuccessful) {
                                println("✅ Registro exitoso: ${response.body()}")
                                // Aquí podrías navegar al login o mostrar un mensaje bonito
                            } else {
                                println("❌ Error al registrar usuario: ${response.errorBody()?.string()}")
                            }
                        } catch (e: Exception) {
                            println("❌ Error en la llamada a la API: ${e.message}")
                        }
                    }
                }
                ,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB9A5EB) // Color moradito
                )
            ) {
                Text(
                    text = "Registrarse",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // ¿Ya tienes cuenta?
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "¿Ya tienes cuenta? ",
                    fontSize = 16.sp,
                    color = Color(0xFF333333)
                )
                Text(
                    text = "Accede",
                    fontSize = 16.sp,
                    color = Color(0xFF6A4ADB),
                    modifier = Modifier.clickable {
                        navController.navigate("loginScreen")
                    }
                )
            }
        }
    }
}
