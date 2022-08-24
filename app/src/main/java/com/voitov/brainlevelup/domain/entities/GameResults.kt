package com.voitov.brainlevelup.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResults(
    val isWinner: Boolean,
    val countOfCorrectAnswers: Int,
    val countOfQuestions: Int,
    val gameplaySettings: GameplaySettings,
) : Parcelable {
    val countOfCorrectAnswersString: String
        get() = countOfCorrectAnswers.toString()
}