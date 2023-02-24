package com.example.vkjunior.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.vkjunior.R
import com.example.vkjunior.databinding.ContactCardBinding
import com.example.vkjunior.databinding.ContactCardPickedBinding
import com.example.vkjunior.domain.Contact
import java.lang.RuntimeException

class ContactAdapter : ListAdapter<Contact, ContactViewHolder>(ContactDiffCallback()) {
    var onContactClickListener: OnContactClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val layout =  when (viewType) {
            UNPICKED_TYPE -> R.layout.contact_card
            PICKED_TYPE -> R.layout.contact_card_picked
            else -> throw RuntimeException("Unknown ViewType")
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = getItem(position)
        val binding = holder.binding

        binding.root.setOnClickListener{
            onContactClickListener?.onContactClickListener(contact)
        }
        when (binding) {
            is ContactCardBinding -> {
                binding.contact = contact
            }
            is ContactCardPickedBinding -> {
                binding.contact = contact
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.isPicked) {
            PICKED_TYPE
        } else {
            UNPICKED_TYPE
        }
    }
    interface OnContactClickListener{
        fun onContactClickListener(contact: Contact)
    }
    companion object {
        const val MAX_POOL_SIZE = 10
        const val UNPICKED_TYPE = 1
        const val PICKED_TYPE = 2
    }
}