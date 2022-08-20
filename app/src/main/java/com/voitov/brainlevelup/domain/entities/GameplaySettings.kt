package com.voitov.brainlevelup.domain.entities

data class GameplaySettings(
    val maxAvailableSumValue: Int,
    val minCountOfCorrectAnswersToWin: Int,
    val minPercentageOfCorrectAnswersToWin: Int,
    val gameTimeInSeconds: Int,
) {
}