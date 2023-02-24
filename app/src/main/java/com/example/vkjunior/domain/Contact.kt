package com.example.vkjunior.domain

data class Contact(
    val name: String,
    val phone: String,
    var isPicked: Boolean,
    val id: Int
)