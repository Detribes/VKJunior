package com.example.vkjunior.ui

import androidx.lifecycle.ViewModel
import com.example.vkjunior.data.ContactRepositoryImpl
import com.example.vkjunior.domain.Contact
import com.example.vkjunior.domain.usecases.AddContactUseCase
import com.example.vkjunior.domain.usecases.GetContactListUseCase
import com.example.vkjunior.domain.usecases.UpdateContactUseCase

class ContactViewModel : ViewModel() {
    private val repository = ContactRepositoryImpl

    private val addContactUseCase = AddContactUseCase(repository)
    private val updateContactUseCase = UpdateContactUseCase(repository)
    private val getContactListUseCase = GetContactListUseCase(repository)

    val contacts = getContactListUseCase.getContactList()

    fun pickContact(contact: Contact) {
        val newContact = contact.copy(isPicked = !contact.isPicked)
        updateContactUseCase.updateContact(newContact)
    }
}