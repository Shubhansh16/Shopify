package com.example.ecommerce.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ecommerce.data.User
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginRegisterViewModel @Inject constructor(
    private val firebaseAuth:FirebaseAuth
) :ViewModel() {



    fun createAccountWithEmailAndPassword(user:User,password:String){
        firebaseAuth.createUserWithEmailAndPassword(user.email,password)
            .addOnSuccessListener {

            }.addOnFailureListener {

            }
    }

}