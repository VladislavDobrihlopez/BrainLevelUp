package com.voitov.brainlevelup.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.voitov.brainlevelup.databinding.FragmentGameplayBinding
import com.voitov.brainlevelup.domain.entities.GameResults
import com.voitov.brainlevelup.presentation.viewmodels.GameplayViewModel

class GameplayFragment : Fragment() {
    private var _viewBinding: FragmentGameplayBinding? = null
    private val viewBinding: FragmentGameplayBinding
        get() = _viewBinding ?: throw RuntimeException("viewBinding in GameplayFragment is null")

    private val textViewOptions by lazy {
        arrayOf(
            viewBinding.textViewOption1,
            viewBinding.textViewOption2,
            viewBinding.textViewOption3,
            viewBinding.textViewOption4,
            viewBinding.textViewOption5,
            viewBinding.textViewOption6,
        )
    }

    private val args by navArgs<GameplayFragmentArgs>()

    private val viewModelFactory: GameViewModelFactory by lazy {
        GameViewModelFactory(args.difficultyLevel, requireActivity().application)
    }

    private val viewModel: GameplayViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(GameplayViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentGameplayBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModelObservers()
        setupClickListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    private fun setupClickListeners() {
        for (textViewOption in textViewOptions) {
            textViewOption.setOnClickListener {
                viewModel.selectAnswer(textViewOption.text.toString().toInt())
            }
        }
    }

    private fun setupViewModelObservers() {
        viewModel.generateQuestion.observe(viewLifecycleOwner) {
            for (i in 0 until textViewOptions.size) {
                textViewOptions[i].text = it.options[i].toString()
            }
            viewBinding.textViewSum.text = it.sum.toString()
            viewBinding.textViewLeftNumber.text = it.visibleValue.toString()
        }
        viewModel.formattedTime.observe(viewLifecycleOwner) {
            viewBinding.textViewTimer.text = it
        }
        viewModel.progressAnswers.observe(viewLifecycleOwner) {
            viewBinding.textViewProgressAnswer.text = it
        }
        viewModel.minPercentageToWin.observe(viewLifecycleOwner) {
            viewBinding.progressBarAnswers.secondaryProgress = it
        }
        viewModel.percentageOfCorrectAnswers.observe(viewLifecycleOwner) {
            viewBinding.progressBarAnswers.setProgress(it, true)
        }
        viewModel.enoughCorrectAnswersToWin.observe(viewLifecycleOwner) {
            viewBinding.textViewProgressAnswer.setTextColor(getColorResIdByState(it))
        }
        viewModel.enoughPercentageToWin.observe(viewLifecycleOwner) {
            val colorResId = getColorResIdByState(it)
            viewBinding.progressBarAnswers.progressTintList =
                ColorStateList.valueOf(colorResId)
        }

        viewModel.gameResults.observe(viewLifecycleOwner) {
            launchGameResultsFragment(it)
        }
    }

    private fun getColorResIdByState(enough: Boolean): Int {
        val colorResId = if (enough) {
            android.R.color.holo_green_light
        } else {
            android.R.color.holo_red_light
        }
        return ContextCompat.getColor(requireContext(), colorResId)
    }

    private fun launchGameResultsFragment(gameResults: GameResults) {
        findNavController().navigate(
            GameplayFragmentDirections.actionGameplayFragmentToGameResultsFragment(
                gameResults
            )
        )
    }
}