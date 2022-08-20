package com.voitov.brainlevelup.domain.entities

import java.io.Serializable

data class GameplaySettings(
    val maxAvailableSumValue: Int,
    val minCountOfCorrectAnswersToWin: Int,
    val minPercentageOfCorrectAnswersToWin: Int,
    val gameTimeInSeconds: Int,
) : Serializable {
}