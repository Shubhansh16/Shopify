package com.example.ecommerce.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerce.data.Products
import com.example.ecommerce.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainCategoryViewModel@Inject constructor(
    private val firestore: FirebaseFirestore
) :ViewModel() {

    private val _specialProducts = MutableStateFlow<Resource<List<Products>>>(Resource.Unspecified())
    val specialProducts :StateFlow<Resource<List<Products>>> = _specialProducts

    private val _bestDealsProducts = MutableStateFlow<Resource<List<Products>>>(Resource.Unspecified())
    val bestDealsProducts :StateFlow<Resource<List<Products>>> = _bestDealsProducts

    private val _bestProducts = MutableStateFlow<Resource<List<Products>>>(Resource.Unspecified())
    val bestProducts :StateFlow<Resource<List<Products>>> = _bestProducts

    init {
        fetchSpecialProducts()
        fetchBestProducts()
        fetchBestDealProducts()
    }

    fun fetchSpecialProducts(){
        viewModelScope.launch{
            _specialProducts.emit(Resource.Loading())
        }
        firestore.collection("Products")
            .whereEqualTo("category", "Special Products").get().addOnSuccessListener { result->
              val specialProductsList = result.toObjects(Products::class.java)
              viewModelScope.launch {
                  _specialProducts.emit(Resource.Success(specialProductsList))
              }
            }.addOnFailureListener {
               viewModelScope.launch {
                   _specialProducts.emit(Resource.Error(it.message.toString()))
               }
            }
        }

    fun fetchBestDealProducts(){
        viewModelScope.launch {
            _bestDealsProducts.emit(Resource.Loading())
        }
        firestore.collection("Products")
            .whereEqualTo("category", "Best Deals").get().addOnSuccessListener { result->
                val bestDealsProducts = result.toObjects(Products::class.java)
                viewModelScope.launch {
                    _bestDealsProducts.emit(Resource.Success(bestDealsProducts))
                }
            }.addOnFailureListener {
                viewModelScope.launch {
                    _bestDealsProducts.emit(Resource.Error(it.message.toString()))
                }
            }
        }

    fun fetchBestProducts(){
        viewModelScope.launch {
            _bestProducts.emit(Resource.Loading())
        }
        firestore.collection("Products").get()
            .addOnSuccessListener { result->
                val bestProducts = result.toObjects(Products::class.java)
                viewModelScope.launch {
                    _bestProducts.emit(Resource.Success(bestProducts))
                }
            }.addOnFailureListener {
                viewModelScope.launch {
                    _bestProducts.emit(Resource.Error(it.message.toString()))
                }
            }
        }
    }