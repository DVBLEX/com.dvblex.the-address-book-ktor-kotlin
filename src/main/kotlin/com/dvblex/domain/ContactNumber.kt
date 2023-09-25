package com.dvblex.domain

import kotlinx.serialization.Serializable

@Serializable
data class ContactNumber(
    val id: String,
    val number: String,
    val contactNumberId:String
)
