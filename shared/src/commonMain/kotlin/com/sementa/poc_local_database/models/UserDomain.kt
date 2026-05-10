package com.sementa.poc_local_database.models

import kotlin.time.Instant

data class UserDomain(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val createdAt: Instant
)