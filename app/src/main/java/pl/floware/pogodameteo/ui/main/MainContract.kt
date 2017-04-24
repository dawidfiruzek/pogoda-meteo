package pl.floware.pogodameteo.ui.main

import io.reactivex.Observable
import pl.floware.pogodameteo.ui.BaseContract

interface MainContract {

    interface View : BaseContract.View {
        fun getWeatherClickedIntent(): Observable<Boolean>
        fun getCommentClickedIntent(): Observable<Boolean>
        fun getSettingsClickedIntent(): Observable<Boolean>
    }

    interface Router : BaseContract.Router

    interface Presenter : BaseContract.Presenter<View, Router>
}
