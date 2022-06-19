package com.example.hydromon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hydromon.databinding.ListAllVariablesAdapterBinding

class AllVariableAdapter : RecyclerView.Adapter<AllVariableAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val bindingLayer = ListAllVariablesAdapterBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(bindingLayer)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 0
    }

    inner class ListViewHolder(private val binding:ListAllVariablesAdapterBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(){

        }
    }
}