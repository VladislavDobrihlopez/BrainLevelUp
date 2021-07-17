package com.voitov.brainlevelup.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.voitov.brainlevelup.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    private var _viewBinding: FragmentWelcomeBinding? = null
    private val viewBinding: FragmentWelcomeBinding
        get() = _viewBinding ?: throw RuntimeException("viewBinding in WelcomeFragment is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.buttonUnderstand.setOnClickListener { }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}