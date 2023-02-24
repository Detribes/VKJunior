package com.example.vkjunior.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vkjunior.databinding.FragmentContactsBinding
import com.example.vkjunior.databinding.FragmentContactsBindingImpl
import com.example.vkjunior.domain.Contact

class ContactsFragment : Fragment() {
    private lateinit var viewModel: ContactViewModel
    private lateinit var _adapter: ContactAdapter
    private lateinit var binding: FragmentContactsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactsBindingImpl.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
        viewModel = ViewModelProvider(this)[ContactViewModel::class.java]
        viewModel.contacts.observe(viewLifecycleOwner) {
            _adapter.submitList(it)
        }
    }

    private fun setupRecycleView() {
        _adapter = ContactAdapter()
        with(binding.rvContactList) {
            adapter = _adapter
            recycledViewPool.setMaxRecycledViews(
                ContactAdapter.UNPICKED_TYPE,
                ContactAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ContactAdapter.PICKED_TYPE,
                ContactAdapter.MAX_POOL_SIZE
            )
        }
        setupOnClickListener()
    }
    private fun setupOnClickListener() {
        _adapter.onContactClickListener = object : ContactAdapter.OnContactClickListener {
            override fun onContactClickListener(contact: Contact) {
                viewModel.pickContact(contact)
            }

        }
    }
}