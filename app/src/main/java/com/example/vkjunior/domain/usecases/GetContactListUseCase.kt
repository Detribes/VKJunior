package com.example.vkjunior.domain.usecases

import androidx.lifecycle.MutableLiveData
import com.example.vkjunior.domain.Contact
import com.example.vkjunior.domain.ContactRepository

class GetContactListUseCase(private val repository: ContactRepository) {
    fun getContactList(): MutableLiveData<List<Contact>> {
        return repository.getContactList()
    }
}