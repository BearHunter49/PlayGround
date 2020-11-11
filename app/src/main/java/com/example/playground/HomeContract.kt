package com.example.playground

interface HomeContract {

    interface HomePresenter{
        fun initData()
        fun setToyDataToAdapter(adapter: ToyAdapter)

    }

    interface HomeView{
        fun setToyAdapter()
        fun updateToyList(adapter: ToyAdapter)
    }

}