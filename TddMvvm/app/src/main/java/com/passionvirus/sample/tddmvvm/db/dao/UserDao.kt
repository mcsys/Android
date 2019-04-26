package com.passionvirus.sample.tddmvvm.db.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.passionvirus.sample.tddmvvm.db.entity.UserEntity

interface UserDao {
    companion object {
        const val TABLE_NAME = "user"
        const val COLUMN_ID = "id"
        const val COLUMN_LATEST_LOGIN = "latest_login_date"
        const val COLUMN_EXPIRED_LOGIN = "expired_login_date"
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity): Long

    @Update
    fun update(user: UserEntity): Int

    @Query("UPDATE " + TABLE_NAME + " SET " + COLUMN_EXPIRED_LOGIN + " = :date")
    fun updateAllUserLoginDate(date: Long): Int

    @Query("DELETE FROM " + TABLE_NAME)
    fun delete()

    @Query("SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_LATEST_LOGIN + " DESC LIMIT 1")
    fun getLatestUser(): UserEntity

    @Query("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = :id")
    fun getUser(id: String): UserEntity

}