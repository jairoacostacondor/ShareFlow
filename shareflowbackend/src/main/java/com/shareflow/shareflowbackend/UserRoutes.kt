package com.shareflow.shareflowbackend

import com.shareflow.shareflowbackend.models.User
import com.shareflow.shareflowbackend.models.UsuariosTable
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import at.favre.lib.crypto.bcrypt.BCrypt
import org.jetbrains.exposed.sql.selectAll
import com.shareflow.shareflowbackend.models.UserResponse
import com.shareflow.shareflowbackend.models.UserLoginRequest
import io.ktor.http.*
import org.jetbrains.exposed.sql.*




fun Route.userRoutes() {

    // ✅ Ruta para registrar usuarios en la base de datos real
    post("/register") {
        val user = call.receive<User>()
        println("✅ Usuario recibido: $user")

        try {
            transaction {


                val hashedPassword = BCrypt.withDefaults().hashToString(12, user.contrasena.toCharArray())

                UsuariosTable.insert {
                    it[UsuariosTable.nombre] = user.nombre
                    it[UsuariosTable.email] = user.email
                    it[UsuariosTable.contrasena] = hashedPassword
                }


            }
            call.respondText("✅ Usuario registrado correctamente: ${user.nombre}")

        } catch (e: Exception) {
            println("❌ Error al registrar usuario: ${e.message}")
            call.respondText("Error al registrar usuario", status = io.ktor.http.HttpStatusCode.InternalServerError)
        }
    }

    // (Opcional) Puedes dejar esta ruta si luego quieres ver usuarios con SELECT
    get("/users") {
        val users = transaction {
            UsuariosTable.selectAll().map {
                UserResponse(
                    id = it[UsuariosTable.id],
                    nombre = it[UsuariosTable.nombre],
                    email = it[UsuariosTable.email]
                )
            }
        }
        call.respond(users)
    }

    post("/login") {
        val loginRequest = call.receive<UserLoginRequest>()

        val user = transaction {
            UsuariosTable.select { UsuariosTable.email eq loginRequest.email }.singleOrNull()
        }

        if (user == null) {
            call.respond(HttpStatusCode.Unauthorized, "❌ Usuario no encontrado")
            return@post
        }

        val hashedPassword = user[UsuariosTable.contrasena]
        val passwordMatches = BCrypt.verifyer().verify(loginRequest.contrasena.toCharArray(), hashedPassword).verified

        if (!passwordMatches) {
            call.respond(HttpStatusCode.Unauthorized, "❌ Contraseña incorrecta")
            return@post
        }

        call.respond(HttpStatusCode.OK, "✅ Login exitoso")
    }


}
