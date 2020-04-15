package com.example.weather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.Model.WeatherInfo
import com.example.weather.data.DataSource
import com.example.weather.data.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel : ViewModel() {

    var weatherInfo: MutableLiveData<WeatherInfo?> = MutableLiveData<WeatherInfo?>()

    fun requestData(cityName: String) {
        val getDataService = Repository.getInstance().create(DataSource::class.java)
        val call = getDataService.getWeatherInfo(cityName)

        call.enqueue(object: Callback<WeatherInfo> {
            override fun onFailure(call: Call<WeatherInfo>, t: Throwable) {
                print("Fail")
                println(t.message)
            }

            override fun onResponse(call: Call<WeatherInfo>, response: Response<WeatherInfo>) {
                println("Success")
                weatherInfo.postValue(response.body())
            }
        })
    }
}
