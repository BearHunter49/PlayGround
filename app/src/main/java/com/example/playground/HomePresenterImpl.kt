package com.example.playground

import com.example.playground.data.ConstData
import com.example.playground.data.Toy

class HomePresenterImpl(private val view: HomeContract.HomeView) : HomeContract.HomePresenter {

    private lateinit var toyList: List<Toy>

    override fun initData() {
        toyList = ConstData.toyListDummyData
    }

    override fun setToyDataToAdapter(adapter: ToyAdapter) {
        adapter.addItem(this.toyList)
        this.view.updateToyList(adapter)
    }

}