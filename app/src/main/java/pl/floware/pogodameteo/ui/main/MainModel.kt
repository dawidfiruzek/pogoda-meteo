package pl.floware.pogodameteo.ui.main

data class MainModel(
        val mainModelElement: MainModelElement
) {
    companion object {
        fun weatherMainModel() = MainModel(MainModelElement.WEATHER)
        fun commentMainModel() = MainModel(MainModelElement.COMMENT)
        fun settingsMainModel() = MainModel(MainModelElement.SETTINGS)
    }
}

enum class MainModelElement {
    WEATHER, COMMENT, SETTINGS
}
