package com.example.vkjunior.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.vkjunior.domain.Contact

class ContactDiffCallback : DiffUtil.ItemCallback<Contact>()  {
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem
    }
}