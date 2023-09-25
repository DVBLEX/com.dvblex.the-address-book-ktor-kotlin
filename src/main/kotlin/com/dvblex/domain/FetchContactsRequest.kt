package com.dvblex.domain

import kotlinx.serialization.Serializable

@Serializable
data class FetchContactsRequest(
    val searchQuery: String
)
