package com.voitov.brainlevelup.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.voitov.brainlevelup.R
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
        Log.d(TAG, "onCreateView2")
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

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart2")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume2")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause2")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop2")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
        Log.d(TAG, "onDestroyView2")
    }

    private fun launchGameplayFragment(difficultyLevel: DifficultyLevel) {
        val args = Bundle().apply {
            putParcelable(GameplayFragment.KEY_DIFFICULTY, difficultyLevel)
        }

        findNavController().navigate(
            R.id.action_chooseDifficultyLevelFragment_to_gameplayFragment,
            args
        )
//        requireActivity().supportFragmentManager.beginTransaction()
//            .replace(R.id.fragmentContainerMain, GameplayFragment.newInstance(difficultyLevel))
//            .addToBackStack(GameplayFragment.NAME)
//            .commit()
    }

    companion object {
        const val NAME = "ChooseDifficultyLevelFragment"
        private const val TAG = "WelcomeFragment"
        fun newInstance(): ChooseDifficultyLevelFragment {
            return ChooseDifficultyLevelFragment()
        }
    }
}