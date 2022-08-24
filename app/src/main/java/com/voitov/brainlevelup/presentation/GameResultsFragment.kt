package com.voitov.brainlevelup.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.voitov.brainlevelup.R
import com.voitov.brainlevelup.databinding.FragmentGameResultsBinding
import com.voitov.brainlevelup.domain.entities.GameResults

class GameResultsFragment : androidx.fragment.app.Fragment() {
    private var _viewBinding: FragmentGameResultsBinding? = null
    private val viewBinding: FragmentGameResultsBinding
        get() = _viewBinding ?: throw RuntimeException("viewBinding in GameResultsFragment is null")

    private val args by lazy {
        GameResultsFragmentArgs.fromBundle(requireArguments())
    }

    private val gameResults: GameResults by lazy {
        args.gameResults
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentGameResultsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        bindViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    private fun setupClickListeners() {
        viewBinding.buttonTryAgain.setOnClickListener {
            retryGame()
        }
    }

    private fun bindViews() {
        viewBinding.gameResults = args.gameResults

        with(viewBinding) {
            imageViewEmojiResult.setImageResource(getSmileResId())

            textViewScorePercentage.text = String.format(
                getString(R.string.score_percentage),
                getScorePercentage().toString()
            )
//            textViewScoreAnswers.text = String.format(
//                getString(R.string.score_answers),
//                gameResults.countOfCorrectAnswers.toString()
//            )

//            val gameplaySettings = gameResults.gameplaySettings
//            textViewRequiredPercentage.text = String.format(
//                getString(R.string.required_percentage),
//                gameplaySettings.minPercentageOfCorrectAnswersToWin.toString()
//            )
//            textViewRequiredAnswers.text = String.format(
//                getString(R.string.required_score),
//                gameplaySettings.minCountOfCorrectAnswersToWin.toString()
//            )
        }
    }

    private fun getSmileResId() =
        if (gameResults.isWinner) {
            R.drawable.ic_smile
        } else {
            R.drawable.ic_sad
        }

    private fun getScorePercentage() =
        if (gameResults.countOfQuestions == 0) {
            0
        } else {
            gameResults.countOfCorrectAnswers * 100 / gameResults.countOfQuestions
        }

    private fun retryGame() {
        findNavController().popBackStack()
    }
}