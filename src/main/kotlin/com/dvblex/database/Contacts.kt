package com.dvblex.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.update
import org.jetbrains.exposed.sql.transactions.transaction

object Contacts : Table() {
    private val contactId = Contacts.varchar("contactId", 100)
    private val firstName = Contacts.varchar("firstName", 150)
    private val lastName = Contacts.varchar("lastName", 150)
    private val contactNumbersId = Contacts.varchar("contactNumbersId", 100)
    private val emailAddressesId = Contacts.varchar("emailAddressesId", 100)

    fun insert(contactDTO: ContactDTO) {
        transaction {
            Contacts.insert {
                it[contactId] = contactDTO.contactId
                it[firstName] = contactDTO.firstName
                it[lastName] = contactDTO.lastName
                it[contactNumbersId] = contactDTO.contactNumbersId
                it[emailAddressesId] = contactDTO.emailAddressesId
            }
        }
    }


    fun fetchContact(id: String): ContactDTO? {
        return try {
            transaction {
                val userModel = Contacts.select { Contacts.contactId.eq(id) }.single()
                ContactDTO(
                    contactId = userModel[Contacts.contactId],
                    firstName = userModel[firstName],
                    lastName = userModel[lastName],
                    contactNumbersId = userModel[contactNumbersId],
                    emailAddressesId = userModel[emailAddressesId]
                )
            }
        } catch (e: Exception) {
            null
        }
    }

    fun fetchAll(): List<ContactDTO> {
        return try {
            transaction {
                Contacts.selectAll().toList()
                    .map {
                        ContactDTO(
                            contactId = it[contactId],
                            firstName = it[firstName],
                            lastName = it[lastName],
                            contactNumbersId = it[contactNumbersId],
                            emailAddressesId = it[emailAddressesId]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun update(contactDTO: ContactDTO): Boolean {
        return try {
            val rowsUpdated = transaction {
                Contacts.update({ Contacts.contactId eq contactDTO.contactId }) {
                    it[contactId] = contactDTO.contactId
                    it[firstName] = contactDTO.firstName
                    it[lastName] = contactDTO.lastName
                    it[contactNumbersId] = contactDTO.contactNumbersId
                    it[emailAddressesId] = contactDTO.emailAddressesId
                }
            }
            rowsUpdated > 0
        } catch (e: Exception) {
            false
        }
    }


    fun delete(contactId: String): Boolean {
        return try {
            val rowsDeleted = transaction {
                Contacts.deleteWhere { Contacts.contactId eq contactId }
            }
            rowsDeleted > 0
        } catch (e: Exception) {
            false
        }
    }


}

