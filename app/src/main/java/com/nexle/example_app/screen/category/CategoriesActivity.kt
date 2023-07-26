package com.nexle.example_app.screen.category

import android.os.Bundle
import android.view.View
import android.view.View.*
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nexle.example_app.R
import com.nexle.example_app.core.activity.BaseActivity
import com.nexle.example_app.screen.category.adapter.CategoriesAdapter

class CategoriesActivity : BaseActivity(R.layout.activity_categories),
    CategoriesAdapter.OnCategoriesClickListener, OnClickListener {

    private lateinit var mViewModel: CategoriesViewModel

    private lateinit var rcvCategories: RecyclerView
    private var mCategoriesAdapter: CategoriesAdapter? = null

    private lateinit var imbBack: ImageButton
    private lateinit var tvNext: TextView


    override fun performDependencyInjection() {
        mViewModel = ViewModelProvider(
            this,
            mViewModelFactory
        )[CategoriesViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        imbBack = findViewById(R.id.imb_back)
        tvNext = findViewById(R.id.tv_done)
        rcvCategories = findViewById(R.id.rcv_categories)

        initData()
        initListener()
    }

    private fun initData() {
        mCategoriesAdapter =
            CategoriesAdapter(this, this)
        rcvCategories.layoutManager = GridLayoutManager(this, 3)
        rcvCategories.adapter = mCategoriesAdapter

        mViewModel.categoryLiveData.observe(this) {
            mCategoriesAdapter?.setItems(it)
            mCategoriesAdapter?.notifyItemRangeChanged(0, it.size)
        }
    }

    private fun initListener() {
        imbBack.setOnClickListener(this)
        tvNext.setOnClickListener(this)
    }

    override fun onCategoryClick(total : Int) {
        tvNext.visibility =  if (total > 0) VISIBLE else INVISIBLE
    }

    override fun onClick(v: View?) {
        if(v == imbBack){
            onBackPressed()
        } else if(v == tvNext){
            Toast.makeText(
                this,
                "Next to Main Activity",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}