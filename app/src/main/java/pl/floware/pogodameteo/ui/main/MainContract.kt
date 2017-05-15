package pl.floware.pogodameteo.ui.main

import io.reactivex.Observable
import pl.floware.pogodameteo.ui.BaseContract

interface MainContract {

    interface View : BaseContract.View {
        fun weatherClickedObservable(): Observable<Boolean>
        fun commentClickedObservable(): Observable<Boolean>
        fun settingsClickedObservable(): Observable<Boolean>

        fun showWeather()
        fun showComment()
        fun showSettings()
    }

    interface Router : BaseContract.Router

    interface Presenter : BaseContract.Presenter<View, Router>
}