package com.voitov.brainlevelup.domain.repositories

import com.voitov.brainlevelup.domain.entities.DifficultyLevel
import com.voitov.brainlevelup.domain.entities.GameplaySettings
import com.voitov.brainlevelup.domain.entities.Question

interface GameRepository {
    fun generateQuestion(maxAvailableSum: Int, countOfAnswersToGenerate: Int): Question
    fun getGameplaySettings(difficultyLevel: DifficultyLevel): GameplaySettings
}