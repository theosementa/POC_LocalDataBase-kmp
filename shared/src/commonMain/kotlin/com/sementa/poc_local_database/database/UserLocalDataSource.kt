package com.sementa.poc_local_database.database

import com.sementa.poc_local_database.database.dao.UserDao
import com.sementa.poc_local_database.database.entities.UserEntity

interface UserLocalDataSource {
    suspend fun insertUser(entity: UserEntity)
    suspend fun deleteUser(userId: Int)
    suspend fun getAllUsers(): List<UserEntity>
}

class DefaultUserLocalDataSource(
    private val userDao: UserDao
): UserLocalDataSource {
    override suspend fun insertUser(entity: UserEntity) {
        userDao.insertUser(entity)
    }

    override suspend fun deleteUser(userId: Int) {
        userDao.deleteUser(userId)
    }

    override suspend fun getAllUsers(): List<UserEntity> {
        return userDao.getAll()
    }

}