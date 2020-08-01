package com.aqube.coroutineexample.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.aqube.coroutineexample.R
import kotlinx.android.synthetic.main.fragment_signup.*

class SignUpFragment : Fragment() {

    private lateinit var viewModel: SignupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonSignUp.setOnClickListener { onSignUp(it) }
        buttonLogin.setOnClickListener { onGotoLogin(it) }

        viewModel = ViewModelProviders.of(this).get(SignupViewModel::class.java)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.signUpComplete.observe(viewLifecycleOwner, Observer { isComplete ->

        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->

        })
    }

    private fun onSignUp(v: View) {
        val action = SignUpFragmentDirections.actionGoToHome()
        Navigation.findNavController(v).navigate(action)
    }

    private fun onGotoLogin(v: View) {
        val action = SignUpFragmentDirections.actionGoToLogin()
        Navigation.findNavController(v).navigate(action)
    }
}