package com.dvblex.plugins

import com.fasterxml.jackson.databind.*
import io.ktor.serialization.gson.*
import io.ktor.serialization.jackson.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
            }
        gson {
            }
        json()
    }
    routing {
        get("/json/jackson") {
                call.respond(mapOf("hello" to "world"))
            }
        get("/json/gson") {
                call.respond(mapOf("hello" to "world"))
            }
        get("/json/kotlinx-serialization") {
                call.respond(mapOf("hello" to "world"))
            }
    }
}
