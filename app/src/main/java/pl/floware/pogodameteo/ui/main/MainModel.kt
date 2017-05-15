package pl.floware.pogodameteo.ui.main

class MainModel(
        val isWeatherVisible: Boolean = false,
        val isCommentVisible: Boolean = false,
        val isSettingsVisible: Boolean = false
) {
    companion object {
        fun weatherMainModel() = MainModel(true, false, false)
        fun commentMainModel() = MainModel(false, true, false)
        fun settingsMainModel() = MainModel(false, false, true)
    }
}