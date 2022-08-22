package com.voitov.brainlevelup.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import com.voitov.brainlevelup.R
import com.voitov.brainlevelup.databinding.FragmentGameResultsBinding
import com.voitov.brainlevelup.domain.entities.GameResult

class GameResultsFragment : Fragment() {
    private var _viewBinding: FragmentGameResultsBinding? = null
    private val viewBinding: FragmentGameResultsBinding
        get() = _viewBinding ?: throw RuntimeException("viewBinding in GameResultsFragment is null")
    private lateinit var gameResult: GameResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArguments()
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

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    retryGame()
                }
            })
    }

    private fun bindViews() {
        with(viewBinding) {
            imageViewEmojiResult.setImageResource(getSmileResId())

            textViewScorePercentage.text = String.format(
                getString(R.string.score_percentage),
                getScorePercentage().toString()
            )
            textViewScoreAnswers.text = String.format(
                getString(R.string.score_answers),
                gameResult.countOfCorrectAnswers.toString()
            )

            val gameplaySettings = gameResult.gameplaySettings
            textViewRequiredPercentage.text = String.format(
                getString(R.string.required_percentage),
                gameplaySettings.minPercentageOfCorrectAnswersToWin.toString()
            )
            textViewRequiredAnswers.text = String.format(
                getString(R.string.required_score),
                gameplaySettings.minCountOfCorrectAnswersToWin.toString()
            )
        }
    }

    private fun getSmileResId() =
        if (gameResult.isWinner) {
            R.drawable.ic_smile
        } else {
            R.drawable.ic_sad
        }

    private fun getScorePercentage() =
        if (gameResult.countOfQuestions == 0) {
            0
        } else {
            gameResult.countOfCorrectAnswers * 100 / gameResult.countOfQuestions
        }

    private fun retryGame() {
        requireActivity().supportFragmentManager.popBackStack(
            GameplayFragment.NAME,
            POP_BACK_STACK_INCLUSIVE
        )
    }

    private fun parseArguments() {
        requireArguments().getParcelable<GameResult>(KEY_RESULTS)?.let {
            gameResult = it
        }
    }

    companion object {
        const val NAME = "GameResultsFragment"
        private const val KEY_RESULTS = "Results"
        fun newInstance(gameResult: GameResult): GameResultsFragment {
            return GameResultsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_RESULTS, gameResult)
                }
            }
        }
    }
}