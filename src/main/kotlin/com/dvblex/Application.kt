package com.dvblex

import com.dvblex.domain.configureContactRouting
import com.dvblex.plugins.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.Database

fun main() {

    /**
     *  To start the application, first, you should define a database connection to PostgreSQL
     *  or any other SQL db but I recommended using PostgreSQL. After the connection to your DB,
     *  you should run the init-db script to create the tables.
     */
    Database.connect(
        url = System.getenv("DATABASE_CONNECTION_STRING"),
        driver = "org.postgresql.Driver",
        user = System.getenv("POSTGRES_USER"),
        password = System.getenv("POSTGRES_PASSWORD")
    )

    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module).start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureSockets()
    configureRouting()
    configureContactRouting()
}
