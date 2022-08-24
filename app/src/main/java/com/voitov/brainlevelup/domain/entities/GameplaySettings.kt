package com.voitov.brainlevelup.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameplaySettings(
    val maxAvailableSumValue: Int,
    val minCountOfCorrectAnswersToWin: Int,
    val minPercentageOfCorrectAnswersToWin: Int,
    val gameTimeInSeconds: Int,
) : Parcelable {
    val minCountOfCorrectAnswersToWinString: String
        get() = minCountOfCorrectAnswersToWin.toString()

    val minPercentageOfCorrectAnswersToWinString: String
        get() = minPercentageOfCorrectAnswersToWin.toString()
}