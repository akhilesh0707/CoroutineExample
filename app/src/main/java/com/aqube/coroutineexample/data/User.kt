package com.aqube.coroutineexample.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val username: String,
    @ColumnInfo(name = "password_hash")
    val passwordHash: Int,
    val address: String,
    val info: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}