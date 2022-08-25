package com.voitov.brainlevelup.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.voitov.brainlevelup.R
import com.voitov.brainlevelup.domain.entities.GameResults

interface OnOptionClickListener {
    fun onOptionClick(option: Int)
}

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

@BindingAdapter("minProgressToWin")
fun bindMinProgressToWin(progressBar: ProgressBar, progress: Int) {
    progressBar.secondaryProgress = progress
}

@BindingAdapter("progressOfCorrectAnswers")
fun bindProgressOfCorrectAnswers(progressBar: ProgressBar, progress: Int) {
    progressBar.setProgress(progress, true)
}

@BindingAdapter("enoughCorrectAnswersToWin")
fun bindEnoughCorrectAnswersToWin(textView: TextView, isEnough: Boolean) {
    textView.setTextColor(getColorResIdByState(textView.context, isEnough))
}

@BindingAdapter("enoughPercentageToWin")
fun bindEnoughPercentageToWin(progressBar: ProgressBar, isEnough: Boolean) {
    val colorResId = getColorResIdByState(progressBar.context, isEnough)
    progressBar.progressTintList =
        ColorStateList.valueOf(colorResId)
}

@BindingAdapter("integerAsText")
fun bindIntegerAsText(textView: TextView, number: Int) {
    textView.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, optionClickListener: OnOptionClickListener) {
    textView.setOnClickListener {
        val number = textView.text.toString().toInt()
        optionClickListener.onOptionClick(number)
    }
}

private fun getColorResIdByState(context: Context, enough: Boolean): Int {
    val colorResId = if (enough) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
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