package com.aqube.coroutineexample.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.aqube.coroutineexample.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonLogout.setOnClickListener { onLogout() }
        buttonDeleteUser.setOnClickListener { onDelete() }

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.logout.observe(viewLifecycleOwner, Observer {

        })
        viewModel.userDeleted.observe(viewLifecycleOwner, Observer {

        })
    }

    private fun onLogout() {
        val action = HomeFragmentDirections.actionGoToSignup()
        Navigation.findNavController(textViewUsername).navigate(action)
    }

    private fun onDelete() {
        val action = HomeFragmentDirections.actionGoToSignup()
        Navigation.findNavController(textViewUsername).navigate(action)
    }
}