package pl.floware.pogodameteo.ui.main

data class MainViewModel(
        val isLoading: Boolean = false,
        val meteogramUrl: String = "",
        val comment: String = ""
) {

    companion object Factory {
        fun getInitialModel(): MainViewModel = MainViewModel()
        fun getLoadingModel(): MainViewModel = MainViewModel()
        fun getErrorModel(): MainViewModel = MainViewModel()
        fun getWeatherModel(meteogramUrl: String = ""): MainViewModel = MainViewModel(meteogramUrl = meteogramUrl)
        fun getCommentModel(): MainViewModel = MainViewModel()
        fun getSettingsModel(): MainViewModel = MainViewModel()
    }
}

