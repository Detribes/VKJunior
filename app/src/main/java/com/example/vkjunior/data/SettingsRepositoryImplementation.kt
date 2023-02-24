package com.example.vkjunior.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vkjunior.domain.SettingsRepository
import com.example.vkjunior.domain.ScreenSettings

object SettingsRepositoryImplementation : SettingsRepository {
    private var screenSettings: ScreenSettings = ScreenSettings(
        true,
        true,
        false
    )
    private val screenSettingsLiveData = MutableLiveData<ScreenSettings>()

    override fun onClickMicro() {
        screenSettings.micActive = !screenSettings.micActive
        updateSettings()
    }

    override fun onClickCamera() {
        screenSettings.cameraActive = !screenSettings.cameraActive
        updateSettings()
    }

    override fun reversePersons() {
       screenSettings.isReversed = !screenSettings.isReversed
        updateSettings()
    }

    override fun getSettings(): LiveData<ScreenSettings> {
        return screenSettingsLiveData
    }
    private fun updateSettings(){
        screenSettingsLiveData.value = screenSettings
    }
}