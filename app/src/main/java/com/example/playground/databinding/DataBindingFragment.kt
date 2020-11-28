package com.example.playground.databinding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.playground.R
import timber.log.Timber

class DataBindingFragment : Fragment() {
    private lateinit var binding: FragmentDataBindingBinding
//    val myText = ObservableField("")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataBindingBinding.inflate(inflater)
        val myViewModel = DataBindingViewModel()

        binding.apply {
            viewModel = myViewModel
            lifecycleOwner = this@DataBindingFragment
//            viewModel = this@DataBindingFragment

            myViewModel.inputText.observe(viewLifecycleOwner){
                Timber.i("Observer 호출")
            }

            button.setOnClickListener {
                myViewModel.btnClick(editText.text.toString())
//                myText.set(editText.text.toString())
            }
        }

        return binding.root
    }

}