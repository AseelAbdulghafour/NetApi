package com.aseel.pets.model

data class Pet(
    val id: Int,
    val name: String,
    val adopted: Boolean,
    val image: String,
    var age: Int,
    val gender: String,
)
