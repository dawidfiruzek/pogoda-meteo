package pl.floware.pogodameteo.ui.main.weather

import pl.floware.pogodameteo.R
import pl.floware.pogodameteo.ui.BaseFragment

class WeatherFragment : BaseFragment(), WeatherContract.View, WeatherContract.Router {

    companion object {
        val TAG = WeatherFragment::class.java.simpleName!!
        fun getInstance() = WeatherFragment()
    }

    override fun layoutId() = R.layout.fragment_weather
}
