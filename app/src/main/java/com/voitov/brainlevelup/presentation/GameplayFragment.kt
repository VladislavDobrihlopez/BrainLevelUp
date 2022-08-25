package com.voitov.brainlevelup.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        setupViewBinding()
        setupViewModelObservers()
        //setupClickListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    private fun setupViewBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.lifecycleOwner = viewLifecycleOwner
    }

    private fun setupViewModelObservers() {
        viewModel.gameResults.observe(viewLifecycleOwner) {
            launchGameResultsFragment(it)
        }
    }

    private fun launchGameResultsFragment(gameResults: GameResults) {
        findNavController().navigate(
            GameplayFragmentDirections.actionGameplayFragmentToGameResultsFragment(
                gameResults
            )
        )
    }
}