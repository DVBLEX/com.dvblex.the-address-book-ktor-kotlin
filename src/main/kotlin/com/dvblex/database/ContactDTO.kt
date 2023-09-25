package com.dvblex.database

import com.dvblex.domain.CreateContactRequest
import com.dvblex.domain.CreateContactResponse
import com.dvblex.domain.UpdateContactRequest
import com.dvblex.domain.UpdateContactResponse
import kotlinx.serialization.Serializable


@Serializable
data class ContactDTO(
    val contactId: String,
    val firstName: String,
    val lastName: String,
    val contactNumbersId: String,
    val emailAddressesId: String
)

fun CreateContactRequest.mapToContactDTO(contactId: String): ContactDTO =
    ContactDTO(
        contactId = contactId,
        firstName = firstName,
        lastName = lastName,
        contactNumbersId = contactNumbersId,
        emailAddressesId = emailAddressesId
    )


fun ContactDTO.mapToCreateContactResponse(): CreateContactResponse =
    CreateContactResponse(
        contactId = contactId,
        firstName = firstName,
        lastName = lastName,
        contactNumbersId = contactNumbersId,
        emailAddressesId = emailAddressesId
    )


fun UpdateContactRequest.mapToContactDTO(contactId: String): ContactDTO =
    ContactDTO(
        contactId = contactId,
        firstName = firstName,
        lastName = lastName,
        contactNumbersId = contactNumbersId,
        emailAddressesId = emailAddressesId
    )

fun ContactDTO.mapToUpdateContactResponse(): UpdateContactResponse =
    UpdateContactResponse(
        contactId = contactId,
        firstName = firstName,
        lastName = lastName,
        contactNumbersId = contactNumbersId,
        emailAddressesId = emailAddressesId
    )
