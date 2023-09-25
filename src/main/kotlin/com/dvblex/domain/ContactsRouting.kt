package com.dvblex.domain

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureContactRouting() {

    routing {
        post("/contacts/create") {
            val request = kotlin.runCatching { call.receiveNullable<CreateContactRequest>() }.getOrNull()
            if (request != null) {
                ContactsController(call).createContact()
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid request or path")
            }
        }

        post("/contacts/search") {
            val request = kotlin.runCatching { call.receiveNullable<FetchContactsRequest>() }.getOrNull()
            if (request != null) {
                ContactsController(call).performSearch(request)
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid request or path")
            }
        }

        get("/contacts/list") {
            ContactsController(call).performList()
        }

        put("/contacts/update/{contactId}") {
            val contactId = call.parameters["contactId"]
            if (contactId != null) {
                ContactsController(call).performUpdate(contactId)
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid contact ID")
            }
        }
        delete("/contacts/delete/{contactId}") {
            val contactId = call.parameters["contactId"]
            if (contactId != null) {
                ContactsController(call).performDelete(contactId)
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid contact ID")
            }
        }
    }
}