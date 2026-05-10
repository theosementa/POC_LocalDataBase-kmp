package com.sementa.poc_local_database.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.time.Clock

@Entity(tableName = "user_entity")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val createdAt: Long = Clock.System.now().toEpochMilliseconds()
)