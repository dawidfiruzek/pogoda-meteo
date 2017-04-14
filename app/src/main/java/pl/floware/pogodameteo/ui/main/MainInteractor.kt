package pl.floware.pogodameteo.ui.main

import io.reactivex.Observable

interface MainInteractor {

    fun getWeatherObservable(): Observable<MainViewModel>
    fun getCommentObservable(): Observable<MainViewModel>
    fun getSettingsObservable(): Observable<MainViewModel>
}
