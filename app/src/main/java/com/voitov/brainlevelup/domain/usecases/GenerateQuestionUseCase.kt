package com.voitov.brainlevelup.domain.usecases

import com.voitov.brainlevelup.domain.entities.Question
import com.voitov.brainlevelup.domain.repositories.GameRepository

class GenerateQuestionUseCase(
    private val repository: GameRepository,
) {
    operator fun invoke(maxAvailableSum: Int): Question {
        return repository.generateQuestion(maxAvailableSum, COUNT_OF_OPTIONS)
    }

    companion object {
        private const val COUNT_OF_OPTIONS = 6
    }
}