package com.example.quizearning.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizearning.ModelClass.HistoryModelClass
import com.example.quizearning.databinding.HistoryItemBinding

class HistoryAdapterClass(var ListHistory:ArrayList<HistoryModelClass>) : RecyclerView.Adapter<HistoryAdapterClass.HistoryCoinViewHolder>() {
    class HistoryCoinViewHolder(var binding: HistoryItemBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryCoinViewHolder {
        return HistoryCoinViewHolder(HistoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount()=ListHistory.size

    override fun onBindViewHolder(holder: HistoryCoinViewHolder, position: Int) {
        holder.binding.Time.text=ListHistory[position].timeAndDate
        holder.binding.coins.text=ListHistory[position].coin
    }
}