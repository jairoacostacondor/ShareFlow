package com.shareflow.app

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import com.shareflow.app.ui.theme.ShareFlowTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewModelScope
import android.util.Log
import com.shareflow.app.User
import com.shareflow.app.network.RetrofitInstance
import retrofit2.HttpException
import java.io.IOException


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShareFlowTheme {
                NavigationGraph()
            }
        }
    }
}

@Composable
fun InitialScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5ECE5))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "ShareFlow",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF7A6E69)
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = { navController.navigate("login") }, // ← aquí va la navegación
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB9A89F)
                )
            ) {
                Text("Log in", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "New user?",
                fontSize = 14.sp,
                color = Color(0xFFB9A89F)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("signup") }, // ← y aquí también
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB9A89F)
                )
            ) {
                Text("Sign in", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }

        Text(
            text = "ShareFlow by Jairo",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp),
            fontSize = 12.sp,
            color = Color(0xFF9C8F89)
        )
    }
}

@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5ECE5))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome back!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF7A6E69)
            )

            Spacer(modifier = Modifier.height(48.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email", color = Color(0xFF7A6E69)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFEDE5DC), RoundedCornerShape(50.dp)),
                shape = RoundedCornerShape(50.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    cursorColor = Color(0xFF7A6E69)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password", color = Color(0xFF7A6E69)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFEDE5DC), RoundedCornerShape(50.dp)),
                shape = RoundedCornerShape(50.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    cursorColor = Color(0xFF7A6E69)
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { /* TODO: Login action */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB9A89F))
            ) {
                Text("Log in", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Forgot password?",
                color = Color(0xFFB9A89F),
                fontSize = 14.sp
            )
        }

        Text(
            text = "Don’t have an account? Sign up",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
                .clickable { navController.navigate("signup") },
            fontSize = 14.sp,
            color = Color(0xFF7A6E69)
        )

    }
}

@Composable
fun SignUpScreen(navController: NavHostController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5ECE5))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Create an account",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF7A6E69)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Enter your details to get started",
                fontSize = 14.sp,
                color = Color(0xFFB9A89F)
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text("Name", color = Color(0xFF7A6E69)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFDEDE5DC), RoundedCornerShape(50.dp)),
                shape = RoundedCornerShape(50.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email", color = Color(0xFF7A6E69)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFDEDE5DC), RoundedCornerShape(50.dp)),
                shape = RoundedCornerShape(50.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password", color = Color(0xFF7A6E69)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFDEDE5DC), RoundedCornerShape(50.dp)),
                shape = RoundedCornerShape(50.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = { Text("Confirm", color = Color(0xFF7A6E69)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFDEDE5DC), RoundedCornerShape(50.dp)),
                shape = RoundedCornerShape(50.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val user = User(name, email, password)

                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val response = RetrofitInstance.api.registerUser(user)
                            withContext(Dispatchers.Main) {
                                if (response.isSuccessful) {
                                    Log.d("SignUp", "✅ Registro exitoso: ${response.body()}")
                                    navController.navigate("login") // si quieres navegar
                                } else {
                                    Log.e("SignUp", "❌ Error del servidor: ${response.code()}")
                                }
                            }
                        } catch (e: IOException) {
                            Log.e("SignUp", "❌ Error de red: ${e.message}")
                        } catch (e: HttpException) {
                            Log.e("SignUp", "❌ Error HTTP: ${e.message}")
                        }
                    }
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB9A89F))
            ) {
                Text("Sign up", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Already have an account? Log in",
                fontSize = 14.sp,
                color = Color(0xFF7A6E69),
                modifier = Modifier
                    .clickable { navController.navigate("login") }
            )
        }
    }
}

