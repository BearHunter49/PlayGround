package com.example.playground

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.playground.data.Toy
import com.example.playground.databinding.ToyItemViewBinding
import com.example.playground.utils.findDestination

class ToyAdapter : RecyclerView.Adapter<ToyItemViewHolder>() {

    private var data: List<Toy> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToyItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ToyItemViewBinding.inflate(layoutInflater, parent, false)

        return ToyItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToyItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = data.size

    fun addItem(item: List<Toy>){
        data = item
    }
}


class ToyItemViewHolder(private val binding: ToyItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(toy: Toy){
//        binding.setVariable(BR.dataToy, toy)
        binding.dataToy = toy
    }
}