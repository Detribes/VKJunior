package com.example.vkjunior.domain.usecases

import com.example.vkjunior.domain.Contact
import com.example.vkjunior.domain.ContactRepository
import com.example.vkjunior.domain.SettingsRepository

class AddContactUseCase(private val repository: ContactRepository) {
    fun addContact(contact: Contact) {
        repository.addContact(contact)
    }
}