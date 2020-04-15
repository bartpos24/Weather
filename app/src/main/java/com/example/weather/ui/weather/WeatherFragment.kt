package com.example.weather.ui.weather

import androidx.lifecycle.ViewModelProviders //
import android.os.Bundle //
import androidx.fragment.app.Fragment //
import android.view.LayoutInflater
import android.view.View //
import android.view.ViewGroup
import android.widget.* //
import androidx.lifecycle.Observer //
import androidx.lifecycle.ViewModelProvider
import com.example.weather.Model.Weather //
import com.example.weather.Model.WeatherInfo //
import com.squareup.picasso.Picasso //
import com.example.weather.R
import java.text.SimpleDateFormat //
import java.sql.Date //
import com.example.weather.ui.weather.WeatherViewModel

class WeatherFragment : Fragment() {

    private lateinit var searchBtn: Button
    private lateinit var searchEditText: EditText
    private lateinit var errorTextView: TextView
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var weatherInfoWrapper: LinearLayout

    private var isAppended = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater!!.inflate(R.layout.weather_fragment, container, false)


        searchBtn = view!!.findViewById(R.id.search_btn)
        weatherInfoWrapper = view!!.findViewById(R.id.weather_info_wrapper)
        errorTextView = view!!.findViewById(R.id.error_text)
        errorTextView.visibility = View.GONE
        searchEditText = view!!.findViewById(R.id.search_editText)

        weatherViewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)

        wireSearchButton()

        weatherViewModel.weatherInfo.observe(this, Observer<WeatherInfo?>{ weatherInfo ->
            if (weatherInfo === null) {
                searchEditText.requestFocus()
                searchEditText.visibility = View.VISIBLE
                errorTextView.text = "Invalid city name, city wasn't found by the Api"
            }
            else {
                if (!isAppended) {
                    val layout = layoutInflater.inflate(R.layout.info_layout, null)
                    weatherInfoWrapper.addView(layout)
                    isAppended = true
                }
                updateWeather(weatherInfo)
            }
        })


        return view
    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        weatherViewModel = ViewModelProviders.of(this).get(WeatherViewModel::class.java)
//
//        wireSearchButton()
//
//        weatherViewModel.weatherInfo.observe(this, Observer<WeatherInfo?>{ weatherInfo ->
//            if (weatherInfo === null) {
//                search_editText.requestFocus()
//                search_editText.visibility = View.VISIBLE
//                error_text.text = "Invalid city name, city wasn't found by the Api"
//            }
//            else {
//                if (!isAppended) {
//                    val layout = layoutInflater.inflate(R.layout.info_layout, null)
//                    weatherInfoWrapper.addView(layout)
//                    isAppended = true
//                }
//                updateWeather(weatherInfo)
//            }
//        })
//    }

    private fun updateWeather(weatherInfo: WeatherInfo) {
        val createdAtFormat = SimpleDateFormat("dd.MM.yyyy hh:mm a")
        val hourMinuteFormat = SimpleDateFormat("hh:mm a")
        val createdAt = createdAtFormat.format(Date(weatherInfo.createdAt!!.toLong() * 1000))
        val sunrise = hourMinuteFormat.format(Date(weatherInfo.sys!!.sunrise!!.toLong() * 1000))
        val sunset = hourMinuteFormat.format(Date(weatherInfo.sys!!.sunset!!.toLong() * 1000))

        val weather: Weather = weatherInfo.list!!.first()

        Picasso.with(activity)
            .load("https://openweathermap.org/img/wn/${ weather.icon }@2x.png")
            .resize(100,100)
            .into(weatherInfoWrapper.findViewById<ImageView>(R.id.weather_icon))

        weatherInfoWrapper.findViewById<TextView>(R.id.weather).text = weather.description
        weatherInfoWrapper.findViewById<TextView>(R.id.city).text = searchEditText.text.toString()
        weatherInfoWrapper.findViewById<TextView>(R.id.temperature).text = weatherInfo.main!!.temperature.toString() + "\u2103"
        weatherInfoWrapper.findViewById<TextView>(R.id.pressure).text = weatherInfo.main!!.pressure.toString() + " hPa"
        weatherInfoWrapper.findViewById<TextView>(R.id.sunrise).text = "Sunrise: " + sunrise
        weatherInfoWrapper.findViewById<TextView>(R.id.sunset).text = "Sunset: " + sunset
        weatherInfoWrapper.findViewById<TextView>(R.id.created_at).text = "Measure time: "+ createdAt
    }
    private fun wireSearchButton() {
        searchBtn.setOnClickListener {
            val city = searchEditText.text.toString()

            if (city.length === 0) {
                searchEditText.requestFocus()
                errorTextView.visibility = View.VISIBLE
                errorTextView.text = "City name cannot be blank"
            }
            else {
                errorTextView.text = ""
                weatherViewModel.requestData(city)
                errorTextView.visibility = View.GONE
            }
        }
    }
}
