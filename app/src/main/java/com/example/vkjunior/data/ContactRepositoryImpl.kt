package com.example.vkjunior.data

import androidx.lifecycle.MutableLiveData
import com.example.vkjunior.domain.Contact
import com.example.vkjunior.domain.ContactRepository
import java.lang.RuntimeException
import kotlin.random.Random
import kotlin.random.nextUInt

object ContactRepositoryImpl : ContactRepository {
    private const val PHONE_CODE = "+79"
    private var contactList =  sortedSetOf<Contact>({ o1, o2 -> o1.id.compareTo(o2.id)})
    private val contactListLiveData = MutableLiveData<List<Contact>>()

    private val setOfNames = setOf<String>("Andrew", "Roman", "Danil", "Eugene", "Alex", "Irina", "Xenia")

    init {
        for (i in 0 until 10) {
            val contact = Contact(
                setOfNames.random(),
                PHONE_CODE+Random.nextInt(100000000, 999999999).toString(),
            false,
                i
            )
            addContact(contact)
        }
    }

    override fun addContact(contact: Contact) {
        contactList.add(contact)
        updateList()
    }

    override fun getContactList(): MutableLiveData<List<Contact>> {
        return contactListLiveData
    }

    override fun updateContact(contact: Contact) {
        val oldElement = getContact(contact.id)
        contactList.remove(oldElement)
        addContact(contact)
    }

    override fun getContact(contactId: Int): Contact {
        return contactList.find { it.id == contactId } ?:
        throw RuntimeException("Element with $contactId ID not found.")
    }

    private fun updateList() {
        contactListLiveData.value = contactList.toList()
    }
}