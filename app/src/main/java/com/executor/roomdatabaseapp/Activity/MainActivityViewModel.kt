package com.executor.roomdatabaseapp.Activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.executor.roomdatabaseapp.Database.Contact
import com.executor.roomdatabaseapp.Database.ContactDatabase

class MainActivityViewModel(app: Application) : AndroidViewModel(app) {
     var allContact: MutableLiveData<List<Contact>>

    init {
        allContact = MutableLiveData()

    }

    fun getAllContactObservers(): MutableLiveData<List<Contact>> {
        return allContact
    }

    fun getAllContact() {
        val contactDao = ContactDatabase.getDatabase((getApplication())).contactDao()
        val list = contactDao.getContact()
        allContact.postValue(list)
    }

    fun insertContactInfo(contact: Contact) {
        val contactDao = ContactDatabase.getDatabase((getApplication())).contactDao()
        contactDao.insertContact(contact)
    }

    fun updateContactInfo(contact: Contact) {
        val contactDao = ContactDatabase.getDatabase(getApplication()).contactDao()
        contactDao.updateContact(contact)
        getAllContact()
    }

    fun deleteContactInfo(contact: Contact) {
        val contactDao = ContactDatabase.getDatabase(getApplication()).contactDao()
        contactDao.deleteContact(contact)
        getAllContact()
    }
}