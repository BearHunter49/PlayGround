package com.example.playground.databinding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.navGraphViewModels
import com.example.playground.R
import timber.log.Timber

class DataBinding2Fragment : Fragment() {
    private val parentViewModel: DataBindingViewModel by navGraphViewModels(R.id.nav_databinding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Timber.d("DataBinding2: ${parentViewModel.inputText.value}")
        parentViewModel.btnClick("From Child!")
        Timber.d("DataBinding2: ${parentViewModel.inputText.value}")

        return inflater.inflate(R.layout.fragment_data_binding2, container, false)
    }
}