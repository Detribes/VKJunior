package com.example.vkjunior.domain.usecases

import com.example.vkjunior.domain.SettingsRepository

class ClickCameraUseCase(private val repository: SettingsRepository) {
    fun clickCamera() {
        repository.onClickCamera()
    }
}