package com.voitov.brainlevelup.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.voitov.brainlevelup.databinding.FragmentGameplayBinding

class GameplayFragment : Fragment() {
    private var _viewBinding: FragmentGameplayBinding? = null
    private val viewBinding: FragmentGameplayBinding
        get() = _viewBinding ?: throw RuntimeException("viewBinding in GameplayFragment is null")

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}