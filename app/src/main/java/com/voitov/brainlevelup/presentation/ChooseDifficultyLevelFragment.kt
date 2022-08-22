package com.voitov.brainlevelup.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.voitov.brainlevelup.databinding.FragmentChooseDifficultyLevelBinding
import com.voitov.brainlevelup.domain.entities.DifficultyLevel

class ChooseDifficultyLevelFragment : Fragment() {
    private var _viewBinding: FragmentChooseDifficultyLevelBinding? = null
    private val viewBinding: FragmentChooseDifficultyLevelBinding
        get() = _viewBinding
            ?: throw RuntimeException("viewBinding in ChooseDifficultyLevelFragment is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentChooseDifficultyLevelBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.buttonLevelTest.setOnClickListener {
            launchGameplayFragment(DifficultyLevel.TEST)
        }
        viewBinding.buttonLevelEasy.setOnClickListener {
            launchGameplayFragment(DifficultyLevel.EASY)
        }
        viewBinding.buttonLevelNormal.setOnClickListener {
            launchGameplayFragment(DifficultyLevel.NORMAL)
        }
        viewBinding.buttonLevelHard.setOnClickListener {
            launchGameplayFragment(DifficultyLevel.HARD)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    private fun launchGameplayFragment(difficultyLevel: DifficultyLevel) {
        findNavController().navigate(
            ChooseDifficultyLevelFragmentDirections.actionChooseDifficultyLevelFragmentToGameplayFragment(
                difficultyLevel
            )
        )
    }
}