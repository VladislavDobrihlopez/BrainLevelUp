package com.voitov.brainlevelup.data

import com.voitov.brainlevelup.domain.entities.DifficultyLevel
import com.voitov.brainlevelup.domain.entities.DifficultyLevel.*
import com.voitov.brainlevelup.domain.entities.GameplaySettings
import com.voitov.brainlevelup.domain.entities.Question
import com.voitov.brainlevelup.domain.repositories.GameRepository
import java.lang.Integer.max
import java.lang.Integer.min
import kotlin.random.Random

object GameRepositoryImpl : GameRepository {
    private const val MIN_AVAILABLE_SUM = 2
    private const val MIN_POSSIBLE_ADDITIVE = 1

    override fun generateQuestion(maxAvailableSum: Int, countOfAnswersToGenerate: Int): Question {
        val sum = Random.nextInt(MIN_AVAILABLE_SUM, maxAvailableSum + 1)
        val visibleAdditive = Random.nextInt(MIN_POSSIBLE_ADDITIVE, sum)

        val correctAnswer = sum - visibleAdditive
        val start = max(correctAnswer - countOfAnswersToGenerate, MIN_POSSIBLE_ADDITIVE)
        val end = min(correctAnswer + countOfAnswersToGenerate, maxAvailableSum)

        val options = HashSet<Int>()

        var isPut = false

        while (options.size < countOfAnswersToGenerate) {
            if (Random.nextBoolean() && !isPut) {
                options.add(correctAnswer)
                isPut = true
            }
            options.add(Random.nextInt(start, end + 1))
        }

        return Question(sum, visibleAdditive, options.toList())
    }

    override fun getGameplaySettings(difficultyLevel: DifficultyLevel): GameplaySettings {
        return when (difficultyLevel) {
            TEST -> GameplaySettings(
                10,
                3,
                50,
                8,
            )
            EASY -> GameplaySettings(
                10,
                10,
                70,
                60,
            )
            NORMAL -> GameplaySettings(
                20,
                20,
                80,
                40,
            )
            HARD -> GameplaySettings(
                30,
                30,
                90,
                25
            )
        }
    }

}