package com.voitov.brainlevelup.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.voitov.brainlevelup.databinding.FragmentGameResultsBinding

class GameResultsFragment : Fragment() {
    private var _viewBinding: FragmentGameResultsBinding? = null
    private val viewBinding: FragmentGameResultsBinding
        get() = _viewBinding ?: throw RuntimeException("viewBinding in GameResultsFragment is null")

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}