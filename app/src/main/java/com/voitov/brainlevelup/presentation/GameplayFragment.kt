package com.voitov.brainlevelup.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.voitov.brainlevelup.R
import com.voitov.brainlevelup.databinding.FragmentGameplayBinding
import com.voitov.brainlevelup.domain.entities.DifficultyLevel
import com.voitov.brainlevelup.domain.entities.GameResult
import com.voitov.brainlevelup.domain.entities.GameplaySettings

class GameplayFragment : Fragment() {
    private var _viewBinding: FragmentGameplayBinding? = null
    private val viewBinding: FragmentGameplayBinding
        get() = _viewBinding ?: throw RuntimeException("viewBinding in GameplayFragment is null")
    private lateinit var difficultyLevel: DifficultyLevel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArguments()
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
        viewBinding.textViewSum.setOnClickListener{
            launchGameResultsFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    private fun parseArguments() {
        val args = requireArguments()
        difficultyLevel = args.getSerializable(KEY_DIFFICULTY) as DifficultyLevel
    }

    private fun launchGameResultsFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerMain, GameResultsFragment.newInstance(GameResult(true, 3,4,
                GameplaySettings(1,2,3,4))))
            .addToBackStack(NAME)
            .commit()
    }

    companion object {
        const val NAME = "GameplayFragment"
        private const val KEY_DIFFICULTY = "KEY_DIFFICULTY"
        fun newInstance(difficultyLevel: DifficultyLevel): GameplayFragment {
            val gameplayFragment = GameplayFragment()
            return gameplayFragment.apply {
                arguments = Bundle().apply {
                    putSerializable(KEY_DIFFICULTY, difficultyLevel)
                }
            }
        }
    }
}