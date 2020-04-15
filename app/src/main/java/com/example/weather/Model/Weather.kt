package com.example.weather.Model

import com.google.gson.annotations.SerializedName

class Weather(
    main: String,
    description: String,
    icon: String
) {
    @SerializedName("main")
    val main: String? = main
    @SerializedName("description")
    val description: String? = description
    @SerializedName("icon")
    val icon: String? = icon

}







//(
//@SerializedName("main")
//val main: String,
//@SerializedName("description")
//val description: String,
//@SerializedName("icon")
//val icon: String
//)