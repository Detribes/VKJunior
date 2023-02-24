package com.example.vkjunior.domain

import androidx.lifecycle.MutableLiveData

interface ContactRepository {
    fun addContact(contact: Contact)
    fun getContactList(): MutableLiveData<List<Contact>>
    fun updateContact(contact: Contact)
    fun getContact(contactId: Int): Contact
}