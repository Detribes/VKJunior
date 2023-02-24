package com.example.vkjunior.domain.usecases

import com.example.vkjunior.domain.Contact
import com.example.vkjunior.domain.ContactRepository

class UpdateContactUseCase(private val repository: ContactRepository) {
    fun updateContact(contact: Contact) {
        repository.updateContact(contact)
    }
}