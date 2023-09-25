package com.dvblex.domain


import kotlinx.serialization.Serializable

@Serializable
data class UpdateContactRequest(
    val firstName: String,
    val lastName: String,
    val contactNumbersId: String,
    val emailAddressesId: String
)

@Serializable
data class UpdateContactResponse(
    val contactId: String,
    val firstName: String,
    val lastName: String,
    val contactNumbersId: String,
    val emailAddressesId: String
)