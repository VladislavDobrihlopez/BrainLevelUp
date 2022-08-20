package com.voitov.brainlevelup.domain.entities

import java.io.Serializable

data class GameResult(
    val isWinner: Boolean,
    val countOfCorrectAnswers: Int,
    val countOfQuestions: Int,
    val gameplaySettings: GameplaySettings,
): Serializable {
}