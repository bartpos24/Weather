package com.example.weather.Model

import com.google.gson.annotations.SerializedName

class WeatherInfo(
    sys: Sys?,
    main: Main?,
    createdAt: String?,
    weather: List<Weather>?,
    cod: Int?,
    message: String?
) {
    @SerializedName("sys")
    val sys: Sys? = sys

    @SerializedName("main")
    val main: Main? = main

    @SerializedName("dt")
    val createdAt: String? = createdAt

    @SerializedName("weather")
    val list: List<Weather>? = weather

    @SerializedName("cod")
    val statusCode: Int? = cod

    @SerializedName("message")
    val message: String? = message

    override fun toString(): String {
        var str = "DT: ${createdAt}\n"
        str += "Code: ${statusCode}\n"
        str += "Message: ${message}\n"
        return str
    }

}