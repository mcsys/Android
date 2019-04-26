package com.passionvirus.sample.tddmvvm.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class UserEntity {
    @PrimaryKey
    private val id: String? = null
    private val step: String = ""
    private val latest_login_date: Long = 0
    private val expired_login_date: Long = 0
    private val error_message: String = ""
}