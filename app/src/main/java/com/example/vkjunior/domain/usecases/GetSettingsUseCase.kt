package com.example.vkjunior.domain.usecases

import androidx.lifecycle.LiveData
import com.example.vkjunior.domain.SettingsRepository
import com.example.vkjunior.domain.ScreenSettings

class GetSettingsUseCase(private val repository: SettingsRepository) {
    fun getSettings() : LiveData<ScreenSettings> {
        return repository.getSettings()
    }
}