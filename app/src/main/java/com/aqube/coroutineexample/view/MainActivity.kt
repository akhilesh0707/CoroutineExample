package com.aqube.coroutineexample.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.aqube.coroutineexample.R
import com.aqube.coroutineexample.model.Country
import com.aqube.coroutineexample.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CountryAdapter.Interaction {

    private val TAG = MainActivity::class.simpleName
    private lateinit var viewModel: ListViewModel
    private val countryAdapter: CountryAdapter by lazy { CountryAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        recyclerViewCountryList.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
            adapter = countryAdapter
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.countries.observe(this, Observer { countries ->
            countries?.let {
                recyclerViewCountryList.visibility = View.VISIBLE
                countryAdapter.swapData(it)
            }
        })

        viewModel.error.observe(this, Observer { isError ->
            textViewError.visibility = if (isError == "") View.GONE else View.VISIBLE
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    textViewError.visibility = View.GONE
                    recyclerViewCountryList.visibility = View.GONE
                }
            }
        })
    }

    override fun countrySelected(model: Country) {
        Log.d(TAG, "Country clicked: $model")
    }


}