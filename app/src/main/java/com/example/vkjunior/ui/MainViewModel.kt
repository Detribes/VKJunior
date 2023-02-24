package com.example.vkjunior.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.vkjunior.data.SettingsRepositoryImplementation
import com.example.vkjunior.domain.ScreenSettings
import com.example.vkjunior.domain.usecases.ClickCameraUseCase
import com.example.vkjunior.domain.usecases.ClickMicroUseCase
import com.example.vkjunior.domain.usecases.GetSettingsUseCase
import com.example.vkjunior.domain.usecases.ReversePersonsUseCase

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = SettingsRepositoryImplementation

    private val clickCameraUseCase = ClickCameraUseCase(repository)
    private val clickMicroUseCase = ClickMicroUseCase(repository)
    private val reversePersonsUseCase = ReversePersonsUseCase(repository)

    val screenSettings: LiveData<ScreenSettings> = GetSettingsUseCase(repository).getSettings()
    fun clickCamera() {
        clickCameraUseCase.clickCamera()
    }
    fun clickMicro() {
        clickMicroUseCase.clickMicro()
    }
    fun reversePersons(){
        reversePersonsUseCase.reversePersons()
    }
}