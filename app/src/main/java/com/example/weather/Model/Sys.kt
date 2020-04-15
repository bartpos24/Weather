package com.example.weather.Model

import com.google.gson.annotations.SerializedName

class Sys(
    sunrise: String,
    sunset: String
) {
    @SerializedName("sunrise")
    val sunrise: String? = sunrise
    @SerializedName("sunset")
    val sunset: String? = sunset

}






//(
//@SerializedName("sunrise")
//val sunrise: String,
//@SerializedName("sunset")
//val sunset: String
//)