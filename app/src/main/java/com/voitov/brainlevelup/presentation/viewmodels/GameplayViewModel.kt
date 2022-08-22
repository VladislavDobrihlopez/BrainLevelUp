package com.voitov.brainlevelup.presentation.viewmodels

import android.app.Application
import android.os.CountDownTimer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.voitov.brainlevelup.R
import com.voitov.brainlevelup.data.GameRepositoryImpl
import com.voitov.brainlevelup.domain.entities.DifficultyLevel
import com.voitov.brainlevelup.domain.entities.GameResult
import com.voitov.brainlevelup.domain.entities.GameplaySettings
import com.voitov.brainlevelup.domain.entities.Question
import com.voitov.brainlevelup.domain.usecases.GenerateQuestionUseCase
import com.voitov.brainlevelup.domain.usecases.GetGameplaySettingUseCase
import java.util.*

class GameplayViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application
    private val gameRepository = GameRepositoryImpl
    private val generateQuestionUseCase = GenerateQuestionUseCase(gameRepository)
    private val getGameplaySettingUseCase = GetGameplaySettingUseCase(gameRepository)

    private lateinit var gameplaySettings: GameplaySettings
    private lateinit var difficultyLevel: DifficultyLevel

    private val _formattedTime = MutableLiveData<String>()
    val formattedTime: LiveData<String>
        get() = _formattedTime

    private var timer: CountDownTimer? = null

    private val _generateQuestion = MutableLiveData<Question>()
    val generateQuestion: LiveData<Question>
        get() = _generateQuestion

    private var countOfQuestions = 0
    private var countOfCorrectAnswers = 0

    private val _progressAnswers = MutableLiveData<String>()
    val progressAnswers: LiveData<String>
        get() = _progressAnswers

    private val _percentageOfCorrectAnswers = MutableLiveData<Int>()
    val percentageOfCorrectAnswers: LiveData<Int>
        get() = _percentageOfCorrectAnswers

    //Для смены цвета в progress bar
    private val _enoughCorrectAnswersToWin = MutableLiveData<Boolean>()
    val enoughCorrectAnswersToWin: LiveData<Boolean>
        get() = _enoughCorrectAnswersToWin

    private val _enoughPercentageToWin = MutableLiveData<Boolean>()
    val enoughPercentageToWin: LiveData<Boolean>
        get() = _enoughPercentageToWin

    //Для secondary progress
    private val _minPercentageToWin = MutableLiveData<Int>()
    val minPercentageToWin: LiveData<Int>
        get() = _minPercentageToWin

    private val _getGameplaySettings = MutableLiveData<GameplaySettings>()
    val getGameplaySettings: LiveData<GameplaySettings>
        get() = _getGameplaySettings

    private val _gameResults = MutableLiveData<GameResult>()
    val gameResults: LiveData<GameResult>
        get() = _gameResults

    private fun loadQuestion() {
        _generateQuestion.value =
            generateQuestionUseCase(gameplaySettings.maxAvailableSumValue)
    }

    fun startGame(difficultyLevel: DifficultyLevel) {
        applySettings(difficultyLevel)
        startTimer()
        loadQuestion()
        updateViewsValues()
    }

    fun selectAnswer(givenAnswer: Int) {
        checkAnswer(givenAnswer)
        updateViewsValues()
        loadQuestion()
    }

    private fun applySettings(difficultyLevel: DifficultyLevel) {
        this.difficultyLevel = difficultyLevel
        gameplaySettings = getGameplaySettingUseCase(difficultyLevel)
        _minPercentageToWin.value = gameplaySettings.minPercentageOfCorrectAnswersToWin
    }

    private fun startTimer() {
        timer = object : CountDownTimer(
            gameplaySettings.gameTimeInSeconds * MILLS_IN_SECOND,
            MILLS_IN_SECOND
        ) {
            override fun onTick(leftTimeInMills: Long) {
                _formattedTime.value = getFormattedTime(leftTimeInMills)
            }

            override fun onFinish() {
                onFinishGame()
            }
        }

        timer?.start()
    }

    private fun getFormattedTime(leftTimeInMills: Long): String {
        val seconds = (leftTimeInMills / MILLS_IN_SECOND).toInt()
        val minutes = seconds / SECONDS_IN_MINUTE
        val leftSeconds = seconds - (minutes * SECONDS_IN_MINUTE)
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, leftSeconds)
    }

    private fun onFinishGame() {
        _gameResults.value =
            GameResult(
                _enoughCorrectAnswersToWin.value == true && _enoughPercentageToWin.value == true,
                countOfCorrectAnswers,
                countOfQuestions,
                gameplaySettings
            )
    }

    private fun checkAnswer(givenAnswer: Int) {
        val expectedAnswer = generateQuestion.value?.answer
        if (givenAnswer == expectedAnswer) {
            countOfCorrectAnswers++
        }
        countOfQuestions++
    }

    private fun calculatePercentageOfCorrectAnswers(): Int {
        if (countOfQuestions == 0) return 0
        return countOfCorrectAnswers * PERCENTAGE_FACTOR / countOfQuestions
    }

    private fun updateViewsValues() {
        val currentPercentage = calculatePercentageOfCorrectAnswers()
        _percentageOfCorrectAnswers.value = currentPercentage

        _progressAnswers.value = String.format(
            context.resources.getString(R.string.progress_answers),
            countOfCorrectAnswers,
            gameplaySettings.minCountOfCorrectAnswersToWin
        )

        _enoughCorrectAnswersToWin.value =
            countOfCorrectAnswers >= gameplaySettings.minCountOfCorrectAnswersToWin
        _enoughPercentageToWin.value =
            currentPercentage >= gameplaySettings.minPercentageOfCorrectAnswersToWin
    }

    override fun onCleared() {
        timer?.cancel()
    }

    companion object {
        private const val MILLS_IN_SECOND = 1000L
        private const val SECONDS_IN_MINUTE = 60
        private const val PERCENTAGE_FACTOR = 100
    }
}