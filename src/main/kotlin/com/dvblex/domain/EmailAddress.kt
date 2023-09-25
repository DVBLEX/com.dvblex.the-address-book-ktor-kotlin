package com.dvblex.domain

import kotlinx.serialization.Serializable

@Serializable
data class EmailAddress(
    val id: String,
    val email: String,
    val emailAddressId: String
)
