package com.example.vkjunior.domain.usecases

import com.example.vkjunior.domain.Contact
import com.example.vkjunior.domain.ContactRepository

class GetContactUseCase(private val repository: ContactRepository) {
    fun getContact(contactId: Int): Contact {
        return repository.getContact(contactId)
    }
}