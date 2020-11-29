package com.example.playground.databinding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableField
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.playground.R
import timber.log.Timber

class DataBindingFragment : Fragment() {
    private lateinit var binding: FragmentDataBindingBinding
//    val myText = ObservableField("")
    private val viewModel: DataBindingViewModel by navGraphViewModels(R.id.nav_databinding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataBindingBinding.inflate(inflater)
        val myViewModel = viewModel

        binding.apply {
            viewModel = myViewModel
            lifecycleOwner = this@DataBindingFragment
//            viewModel = this@DataBindingFragment

            myViewModel.inputText.observe(viewLifecycleOwner){
                Timber.i("Observer 호출, ${myViewModel.inputText.value}")
            }

            button.setOnClickListener {
                myViewModel.btnClick(editText.text.toString())
//                myText.set(editText.text.toString())
            }

            nextButton.setOnClickListener {
                findNavController().navigate(R.id.action_dataBindingFragment_to_dataBinding2Fragment)
            }
        }

        return binding.root
    }

}