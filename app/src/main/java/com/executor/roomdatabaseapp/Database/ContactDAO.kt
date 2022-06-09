package com.executor.roomdatabaseapp.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.executor.roomdatabaseapp.Database.Contact

@Dao
interface ContactDAO {
    @Insert
     fun insertContact(contact: Contact)

    @Update
     fun updateContact(contact: Contact)

    @Delete
     fun deleteContact(contact: Contact)

    @Query("select * from contact order by id desc")
    fun getContact(): List<Contact>
}