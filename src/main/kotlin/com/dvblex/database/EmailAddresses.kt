package com.dvblex.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction


object EmailAddresses : Table() {
    private val id = EmailAddresses.varchar("id", 50)
    private val email = EmailAddresses.varchar("email", 50)
    private val emailAddressId = EmailAddresses.varchar("emailAddressId", 100)

    fun insert(emailAddressDTO: EmailAddressDTO) {
        transaction {
            EmailAddresses.insert {
                it[id] = emailAddressDTO.id
                it[email] = emailAddressDTO.email
                it[emailAddressId] = emailAddressDTO.emailAddressId
            }
        }
    }

    fun fetchEmails(): List<EmailAddressDTO> {
        return try {
            transaction {
                EmailAddresses.selectAll().toList()
                    .map {
                        EmailAddressDTO(
                            id = it[EmailAddresses.id],
                            email = it[EmailAddresses.email],
                            emailAddressId = it[EmailAddresses.emailAddressId]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}