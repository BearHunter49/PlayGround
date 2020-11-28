package com.example.playground

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.playground.data.ConstData
import com.example.playground.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), HomeContract.HomeView {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var presenter: HomeContract.HomePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        presenter = HomePresenterImpl(this)

        presenter.initData()
        
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToyAdapter()
    }

    /**
     * Adapter는 View와 Model 성격 모두 갖고 있다.
     * View가 맡는것이 맞나?
     */
    override fun setToyAdapter() {
        val adapter = ToyAdapter()

        binding.toyList.adapter = adapter
        presenter.setToyDataToAdapter(adapter)
    }

    override fun updateToyList(adapter: ToyAdapter) {
        adapter.notifyDataSetChanged()
    }


}