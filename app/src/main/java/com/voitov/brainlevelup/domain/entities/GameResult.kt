package com.voitov.brainlevelup.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResult(
    val isWinner: Boolean,
    val countOfCorrectAnswers: Int,
    val countOfQuestions: Int,
    val gameplaySettings: GameplaySettings,
) : Parcelable