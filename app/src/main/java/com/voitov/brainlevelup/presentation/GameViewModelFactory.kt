package com.voitov.brainlevelup.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.voitov.brainlevelup.domain.entities.DifficultyLevel
import com.voitov.brainlevelup.presentation.viewmodels.GameplayViewModel

open class GameViewModelFactory(
    private val difficultyLevel: DifficultyLevel,
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameplayViewModel::class.java)) {
            return GameplayViewModel(
                difficultyLevel = difficultyLevel,
                application = application
            ) as T
        }
        throw RuntimeException("Unknown viewModel $modelClass")
    }
}