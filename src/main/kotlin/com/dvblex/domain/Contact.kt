package com.dvblex.domain

import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    val id: String,
    val firstName: String,
    val lastName: String,
    val contactNumbers: List<ContactNumber>,
    val emailAddresses: List<EmailAddress>
)
