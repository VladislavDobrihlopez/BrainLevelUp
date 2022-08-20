package com.voitov.brainlevelup.domain.entities

data class GameResult(
    val isWinner: Boolean,
    val countOfCorrectAnswers: Int,
    val countOfQuestions: Int,
    val gameplaySettings: GameplaySettings,
) {
}