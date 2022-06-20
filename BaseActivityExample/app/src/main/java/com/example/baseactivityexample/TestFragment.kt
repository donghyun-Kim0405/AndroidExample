package com.example.baseactivityexample

import android.os.Bundle
import android.view.View
import com.example.baseactivityexample.databinding.FragmentTestBinding

class TestFragment : BaseFragment<FragmentTestBinding>(R.layout.fragment_test) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnFragment.setOnClickListener {
            showMessage("Fragment")
        }

    }

}