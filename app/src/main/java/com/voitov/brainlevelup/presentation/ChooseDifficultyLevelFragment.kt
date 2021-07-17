package com.voitov.brainlevelup.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.voitov.brainlevelup.databinding.FragmentChooseDifficultyLevelBinding

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    companion object {
        fun newInstance(): ChooseDifficultyLevelFragment {
            return ChooseDifficultyLevelFragment()
        }
    }
}