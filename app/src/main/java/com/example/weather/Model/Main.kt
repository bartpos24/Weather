package com.example.weather.Model

import com.google.gson.annotations.SerializedName

class Main(
    temperature: Double,
    pressure: Double
) {
    @SerializedName("temp")
    val temperature: Double? = temperature
    @SerializedName("pressure")
    val pressure: Double? = pressure
}




//(
//@SerializedName("temp")
//val temperature: Double,
//@SerializedName("pressure")
//val pressure: Double
//)