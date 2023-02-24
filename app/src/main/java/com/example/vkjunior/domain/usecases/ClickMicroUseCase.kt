package com.example.vkjunior.domain.usecases

import com.example.vkjunior.domain.SettingsRepository

class ClickMicroUseCase(private val repository: SettingsRepository) {
    fun clickMicro() {
        repository.onClickMicro()
    }
}