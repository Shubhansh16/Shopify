package com.example.ecommerce.data

data class User(
    val firstName:String,
    val lastName:String,
    val email:String,
    var imagePath:String =""
) {
    constructor(): this("","","","")
}