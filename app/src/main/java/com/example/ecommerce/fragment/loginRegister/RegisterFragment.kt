package com.example.ecommerce.fragment.loginRegister

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.ecommerce.R
import com.example.ecommerce.data.User
import com.example.ecommerce.databinding.FragmentRegisterBinding
import com.example.ecommerce.util.Resource
import com.example.ecommerce.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings


private val TAG ="RegisterFragment"
@AndroidEntryPoint
class RegisterFragment:Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
           buttonRegister.setOnClickListener {
               val user = User(
               edFirstName.text.toString().trim(),
               edLastName.text.toString().trim(),
               edEmailRegister.text.toString().trim()
               )
               val password = edPasswordRegister.text.toString()
               viewModel.createAccountWithEmailAndPassword(user, password)
           }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.register.collect{
                when(it){
                    is Resource.Loading -> {
                      binding.buttonRegister.startAnimation()
                    }
                    is Resource.Success -> {
                        Log.d("test",it.data.toString())
                        binding.buttonRegister.revertAnimation()
                    }
                    is Resource.Error ->{
                        Log.d(TAG,it.message.toString())
                        binding.buttonRegister.revertAnimation()
                    }
                    else -> Unit
                }
            }
        }
    }
}