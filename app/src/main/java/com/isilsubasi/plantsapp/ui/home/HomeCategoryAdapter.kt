package com.isilsubasi.plantsapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.isilsubasi.plantsapp.databinding.CategoryItemBinding
import com.isilsubasi.plantsapp.model.CategoryResponseItem
import com.isilsubasi.plantsapp.util.loadImage

class HomeCategoryAdapter(var list: List<CategoryResponseItem>?,
                          var onItemClickListener: ItemClickListener) : RecyclerView.Adapter<HomeCategoryAdapter.MyViewHolder>() {


    inner class MyViewHolder(val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding=CategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        with(holder){
            binding.apply {
                txtCategory.text=list!!.get(position).CategoryName
                imageCategory.loadImage(list!!.get(position).CategoryImage)
            }
        }
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(position)
        }

    }

    override fun getItemCount(): Int {
        if (list == null)
            return 0
        else
            return  list!!.size
    }

    fun setData(filterCategory:List<CategoryResponseItem>) {
        list = filterCategory
    }


}