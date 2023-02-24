package com.example.vkjunior.domain

import androidx.lifecycle.LiveData

interface SettingsRepository {
    fun onClickMicro()
    fun onClickCamera()
    fun reversePersons()
    fun getSettings() : LiveData<ScreenSettings>
}