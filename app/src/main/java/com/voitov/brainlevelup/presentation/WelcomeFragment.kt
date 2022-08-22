package com.voitov.brainlevelup.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.voitov.brainlevelup.R
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
        Log.d(TAG, "onCreateView")
        viewBinding.buttonUnderstand.setOnClickListener {
            launchChooseDifficultyLevelFragment()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
        Log.d(TAG, "onDestroyView")
    }

    private fun launchChooseDifficultyLevelFragment() {
        findNavController().navigate(R.id.action_welcomeFragment_to_chooseDifficultyLevelFragment)
//        requireActivity().supportFragmentManager.beginTransaction()
//            .replace(R.id.fragmentContainerMain, ChooseDifficultyLevelFragment.newInstance())
//            .addToBackStack(ChooseDifficultyLevelFragment.NAME)
//            .commit()
    }

    companion object {
        private const val TAG = "WelcomeFragment"
    }
}