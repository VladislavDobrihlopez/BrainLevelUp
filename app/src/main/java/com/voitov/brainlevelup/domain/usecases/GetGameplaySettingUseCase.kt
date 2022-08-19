package com.voitov.brainlevelup.domain.usecases

import com.voitov.brainlevelup.domain.entities.DifficultyLevel
import com.voitov.brainlevelup.domain.entities.GameplaySettings
import com.voitov.brainlevelup.domain.repositories.GameRepository

class GetGameplaySettingUseCase(
    private val repository: GameRepository,
) {
    operator fun invoke(difficultyLevel: DifficultyLevel): GameplaySettings {
        return repository.getGameplaySettings(difficultyLevel)
    }
}