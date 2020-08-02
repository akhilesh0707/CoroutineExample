package com.aqube.coroutineexample.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aqube.coroutineexample.R
import com.aqube.coroutineexample.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ListViewModel
    private val newsListAdapter: NewsListAdapter by lazy { NewsListAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        recyclerViewNewsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsListAdapter
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.newArticles.observe(this, Observer { article ->
            progressBar.visibility = View.GONE
            recyclerViewNewsList.visibility = View.VISIBLE
            newsListAdapter.onAddNewsItem(article)
            recyclerViewNewsList.smoothScrollToPosition(0)
        })
    }

}