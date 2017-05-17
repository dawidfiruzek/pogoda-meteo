package pl.floware.pogodameteo.ui.main.weather

data class WeatherModel(val photoUrl: String) {

    companion object {
        val errorUrl = "https://i.imgflip.com/ban8r.jpg"
        fun successWeatherModel(photoUrl: String) = WeatherModel(photoUrl)
        fun errorWeatherModel() = WeatherModel(errorUrl)
    }
}