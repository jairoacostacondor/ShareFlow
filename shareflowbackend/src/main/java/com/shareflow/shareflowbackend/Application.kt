package com.shareflow.shareflowbackend

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json
import com.shareflow.shareflowbackend.userRoutes

fun main() {
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        // 👉 Aquí conectamos con la base de datos
        DatabaseFactory.init()

        routing {
            userRoutes()
        }
    }.start(wait = true)
}
