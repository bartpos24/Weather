package com.example.weather.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository {
    companion object {
        private var retrofit: Retrofit? = null
        private val BASE_URL = "https://api.openweathermap.org/data/2.5/"

        fun getInstance(): Retrofit {
            if (retrofit === null) {
                retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit!!
        }
    }
}