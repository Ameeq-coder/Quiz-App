package com.example.quizearning.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.quizearning.ModelClass.CategoryModelClass
import com.example.quizearning.Quiz
import com.example.quizearning.databinding.CategoryitemBinding

class CategoryAdapter(
    var categorylist: ArrayList<CategoryModelClass>,
    var requireActivity: FragmentActivity
) : RecyclerView.Adapter<CategoryAdapter.MyCategoryViewHoldER>() {
    class MyCategoryViewHoldER(var binding: CategoryitemBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCategoryViewHoldER {
     return  MyCategoryViewHoldER(CategoryitemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount()=categorylist.size

    override fun onBindViewHolder(holder: MyCategoryViewHoldER, position: Int) {
        var datalist=categorylist[position]
        holder.binding.categoryimg.setImageResource(datalist.catimage)
        holder.binding.categorytext.text=datalist.catText
        holder.binding.categorybtn.setOnClickListener {
            var intent=Intent(requireActivity,Quiz::class.java)
            intent.putExtra("categoryimg",datalist.catimage)
            intent.putExtra("questiontype",datalist.catText)
        requireActivity.startActivity(intent)
        }

    }
}