package pl.floware.pogodameteo.ui.main.weather

import io.reactivex.Observable
import pl.floware.pogodameteo.ui.BaseContract

interface WeatherContract {

    interface View : BaseContract.View {
        fun getRefreshObservable(): Observable<Any>

        fun showImage(url: String)
    }

    interface Router : BaseContract.Router

    interface Presenter : BaseContract.Presenter<View, Router>
}