package com.sementa.poc_local_database.repositories

import com.sementa.poc_local_database.database.UserLocalDataSource
import com.sementa.poc_local_database.database.entities.UserEntity
import com.sementa.poc_local_database.models.UserDomain
import kotlin.time.Instant

interface UserRepository {
    suspend fun insertUser(firstName: String, lastName: String, age: Int)
    suspend fun deleteUser(userId: Int)
    suspend fun fetchAllUsers(): List<UserDomain>
}

class DefaultUserRepository(
    private val dataSource: UserLocalDataSource
): UserRepository {
    override suspend fun insertUser(
        firstName: String,
        lastName: String,
        age: Int
    ) {
        val entity = UserEntity(firstName = firstName, lastName = lastName, age = age)
        dataSource.insertUser(entity)
    }

    override suspend fun deleteUser(userId: Int) {
        dataSource.deleteUser(userId)
    }

    override suspend fun fetchAllUsers(): List<UserDomain> {
        return dataSource.getAllUsers().map {
            UserDomain(it.id, it.firstName, it.lastName, it.age, Instant.fromEpochMilliseconds(it.createdAt))
        }
    }

}