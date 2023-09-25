package com.dvblex.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object ContactNumbers : Table() {
    private val id = ContactNumbers.varchar("id", 50)
    private val number = ContactNumbers.varchar("number", 50)
    private val contactNumberId = ContactNumbers.varchar("contactNumberId", 100)

    fun insert(contactNumberDTO: ContactNumberDTO) {
        transaction {
            ContactNumbers.insert {
                it[id] = contactNumberDTO.id
                it[number] = contactNumberDTO.number
                it[contactNumberId] = contactNumberDTO.contactNumberId
            }
        }
    }

    fun fetchNumbers(): List<ContactNumberDTO> {
        return try {
            transaction {
                ContactNumbers.selectAll().toList()
                    .map {
                        ContactNumberDTO(
                            id = it[ContactNumbers.id],
                            number = it[ContactNumbers.number],
                            contactNumberId = it[ContactNumbers.contactNumberId]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}