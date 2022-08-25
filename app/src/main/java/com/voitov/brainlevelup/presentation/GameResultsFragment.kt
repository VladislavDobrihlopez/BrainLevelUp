package com.voitov.brainlevelup.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.voitov.brainlevelup.databinding.FragmentGameResultsBinding
import com.voitov.brainlevelup.domain.entities.GameResults

class GameResultsFragment : androidx.fragment.app.Fragment() {
    private var _viewBinding: FragmentGameResultsBinding? = null
    private val viewBinding: FragmentGameResultsBinding
        get() = _viewBinding ?: throw RuntimeException("viewBinding in GameResultsFragment is null")

    private val args by lazy {
        GameResultsFragmentArgs.fromBundle(requireArguments())
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
        viewBinding.gameResults = args.gameResults
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

    private fun retryGame() {
        findNavController().popBackStack()
    }
}