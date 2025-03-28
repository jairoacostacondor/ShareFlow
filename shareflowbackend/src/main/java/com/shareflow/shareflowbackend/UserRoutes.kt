package com.shareflow.shareflowbackend

import com.shareflow.shareflowbackend.models.User
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

val users = mutableListOf<User>()
val mutex = Mutex()

fun Route.userRoutes() {

    // Ruta para registrar usuarios
    post("/register") {
        val user = call.receive<User>()
        println("✅ Usuario recibido: $user")
        mutex.withLock {
            users.add(user)
        }
        call.respondText("Usuario registrado: ${user.name}")
    }

    // Ruta para ver todos los usuarios (debug opcional)
    get("/users") {
        call.respond(users)
    }
}
