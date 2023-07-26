package com.nexle.example_app.screen.category.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nexle.example_app.R
import com.nexle.example_app.model.CategoryModel

class CategoriesAdapter(
    private val mContext: Context,
    private var mOnCategoriesClickListener: OnCategoriesClickListener
) : RecyclerView.Adapter<CategoriesAdapter.CategoriesHolder>() {

    private var mItems: ArrayList<CategoryModel>? = null
    private var total = 0

    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        return CategoriesHolder(
            LayoutInflater.from(mContext).inflate(
                R.layout.item_category,
                parent,
                false
            )
        )
    }

    override
    fun getItemCount(): Int {
        mItems?.let {
            return it.size
        }
        return 0
    }

    fun setItems(list: List<CategoryModel>): CategoriesAdapter {
        if (mItems == null) {
            mItems = ArrayList()
        }
        mItems?.let {
            it.clear()
            it.addAll(list)
        }
        return this
    }

    override
    fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        val data = mItems?.get(position)
        data?.let {
            holder.tvCategory.text = data.name
            holder.tvCategory.isSelected = data.isSelected
        }
    }

    inner class CategoriesHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var tvCategory : TextView
        init {
            tvCategory = itemView.findViewById(R.id.tv_category)
            tvCategory.setOnClickListener(this)
        }

        override
        fun onClick(v: View?) {
            if (v == tvCategory) {
                val data = mItems?.get(adapterPosition)
                if(data != null){
                    data.isSelected = !data.isSelected
                    if(data.isSelected) total++ else total--
                    notifyItemChanged(adapterPosition)
                }
                mOnCategoriesClickListener.onCategoryClick(total)
            }
        }
    }

    public interface OnCategoriesClickListener {
        fun onCategoryClick(total : Int)
    }
}