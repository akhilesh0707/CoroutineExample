package com.aqube.coroutineexample.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.aqube.coroutineexample.R
import com.aqube.coroutineexample.util.showToastShort
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonLogin.setOnClickListener { onLogin() }
        buttonSignup.setOnClickListener { onGotoSignUp(it) }

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.loginComplete.observe(viewLifecycleOwner, Observer {
            context?.showToastShort("Login complete")
            val action = LoginFragmentDirections.actionGoToHome()
            Navigation.findNavController(editTextUsername).navigate(action)
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            context?.showToastShort("Error: $error")
        })
    }

    private fun onLogin() {
        val username = editTextUsername.text.toString()
        val password = editTextPassword.text.toString()
        if (username.isEmpty() || password.isEmpty()) {
            context?.showToastShort("Please fill all fields")
        } else {
            viewModel.login(username, password)
        }
    }

    private fun onGotoSignUp(v: View) {
        val action = LoginFragmentDirections.actionGoToSignup()
        Navigation.findNavController(v).navigate(action)
    }
}