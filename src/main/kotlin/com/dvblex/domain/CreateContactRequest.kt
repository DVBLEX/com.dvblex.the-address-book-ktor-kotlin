package com.dvblex.domain

import kotlinx.serialization.Serializable

@Serializable
data class CreateContactRequest(
    val contactId: String,
    val firstName: String,
    val lastName: String,
    val contactNumbersId: String,
    val emailAddressesId: String
)

@Serializable
data class CreateContactResponse(
    val contactId: String,
    val firstName: String,
    val lastName: String,
    val contactNumbersId: String,
    val emailAddressesId: String
)