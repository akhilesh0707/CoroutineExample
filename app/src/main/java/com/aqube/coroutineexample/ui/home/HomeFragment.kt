package com.aqube.coroutineexample.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.aqube.coroutineexample.R
import com.aqube.coroutineexample.data.LoginState
import com.aqube.coroutineexample.util.showToastShort
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

        textViewUsername.text = LoginState.user?.username

        buttonLogout.setOnClickListener { onLogout() }
        buttonDeleteUser.setOnClickListener { onDelete() }

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.logout.observe(viewLifecycleOwner, Observer {
            context?.showToastShort("Logged out")
            goToSignUpScreen()
        })
        viewModel.userDeleted.observe(viewLifecycleOwner, Observer {
            context?.showToastShort("User deleted")
            goToSignUpScreen()
        })
    }

    private fun goToSignUpScreen() {
        val action = HomeFragmentDirections.actionGoToSignup()
        Navigation.findNavController(textViewUsername).navigate(action)
    }

    private fun onLogout() {
        viewModel.onLogout()
    }

    private fun onDelete() {
        activity?.let {
            AlertDialog.Builder(it)
                .setTitle("Delete user")
                .setMessage("Are you sure you want to delete user")
                .setPositiveButton("Yes") { _, _ -> viewModel.onDeleteUser() }
                .setNegativeButton("Cancel", null)
                .create()
                .show()
        }
    }
}