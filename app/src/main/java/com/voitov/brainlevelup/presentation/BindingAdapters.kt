package com.voitov.brainlevelup.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.voitov.brainlevelup.R
import com.voitov.brainlevelup.domain.entities.GameResults

@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, isWinner: Boolean) {
    imageView.setImageResource(getEmojiResId(isWinner))
}

private fun getEmojiResId(isWinner: Boolean) =
    if (isWinner) {
        R.drawable.ic_smile
    } else {
        R.drawable.ic_sad
    }

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, minCountOfCorrectAnswersToWin: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        minCountOfCorrectAnswersToWin.toString()
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, countOfCorrectAnswers: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        countOfCorrectAnswers
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, requiredPercentage: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        requiredPercentage
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResults: GameResults) {
    textView.text = String.format(
        textView.context.getString(R.string.score_percentage),
        getScorePercentage(gameResults).toString()
    )
}

private fun getScorePercentage(gameResults: GameResults) =
    with(gameResults) {
        if (countOfQuestions == 0) {
            ZERO_PERCENT
        } else {
            countOfCorrectAnswers * PERCENTAGE_FACTOR / countOfQuestions
        }
    }

private const val ZERO_PERCENT = 0
private const val PERCENTAGE_FACTOR = 100