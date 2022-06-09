package com.executor.roomdatabaseapp.Database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "contact")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val fName: String,
    val lName: String,
    val dob: Int,
    val phone: Int,
    val createDate: Date,
)
