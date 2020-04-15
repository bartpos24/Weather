package com.example.weather.data


import com.example.weather.Model.WeatherInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DataSource {
    companion object {
        private val SECRET_KEY: String = "bc5326214f965b1acad7d32132cdaa33"
    }

    @GET("weather")
    fun getWeatherInfo(
        @Query("q") cityName: String,
        @Query("units") units: String = "metric",
        @Query("appid") secretKey: String = SECRET_KEY
    ): Call<WeatherInfo>
}