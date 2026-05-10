package com.sementa.poc_local_database.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sementa.poc_local_database.database.entities.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user_entity")
    suspend fun getAll(): List<UserEntity>

    @Insert
    suspend fun insertUser(entity: UserEntity)

    @Query("DELETE FROM user_entity WHERE id = :userId")
    suspend fun deleteUser(userId: Int)

}
