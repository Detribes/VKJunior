package com.example.vkjunior.domain.usecases

import com.example.vkjunior.domain.SettingsRepository

class ReversePersonsUseCase(private val repository: SettingsRepository) {
    fun reversePersons() {
        repository.reversePersons()
    }
}