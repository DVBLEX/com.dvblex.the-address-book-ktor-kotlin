package com.dvblex.domain

import com.dvblex.database.Contacts
import com.dvblex.database.mapToContactDTO
import com.dvblex.database.mapToCreateContactResponse
import com.dvblex.database.mapToUpdateContactResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class ContactsController(private val call: ApplicationCall) {


    suspend fun performSearch(request: FetchContactsRequest) {
        if (request.searchQuery.isBlank()) {
            call.respond(Contacts.fetchAll())
        } else {
            call.respond(Contacts.fetchAll().filter { it.contactId.contains(request.searchQuery, ignoreCase = true) })
        }
    }


    suspend fun createContact() {
        val request = call.receive<CreateContactRequest>()
        val contact = request.mapToContactDTO(UUID.randomUUID().toString())
        Contacts.insert(contact)
        call.respond(contact.mapToCreateContactResponse())

    }

    suspend fun performList() {
        call.respond(Contacts.fetchAll())
    }

    suspend fun performUpdate(contactId: String) {
        val existingContact = Contacts.fetchContact(contactId)
        if (existingContact != null) {
            val request = call.receive<UpdateContactRequest>()
            val updatedContact = request.mapToContactDTO(contactId)
            val isUpdated = Contacts.update(updatedContact)
            if (isUpdated) {
                call.respond(updatedContact.mapToUpdateContactResponse())
            } else {
                call.respond(HttpStatusCode.InternalServerError, "Contact update failed")
            }
        } else {
            call.respond(HttpStatusCode.NotFound, "Contact not found")
        }
    }


    suspend fun performDelete(contactId: String) {
        val isDeleted = Contacts.delete(contactId)
        if (isDeleted) {
            call.respond(HttpStatusCode.NoContent)
        } else {
            call.respond(HttpStatusCode.NotFound, "Contact not found")
        }
    }


}
