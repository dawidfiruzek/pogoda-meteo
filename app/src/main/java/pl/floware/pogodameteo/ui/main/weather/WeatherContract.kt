package pl.floware.pogodameteo.ui.main.weather

import pl.floware.pogodameteo.ui.BaseContract

interface WeatherContract {

    interface View : BaseContract.View

    interface Router : BaseContract.Router

    interface Presenter : BaseContract.Presenter<View, Router>
}