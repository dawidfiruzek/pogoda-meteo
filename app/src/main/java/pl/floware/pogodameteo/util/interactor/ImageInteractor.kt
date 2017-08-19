package pl.floware.pogodameteo.util.interactor

import io.reactivex.Observable

interface ImageInteractor {
    fun imageObservable(weatherUrl: String): Observable<String>
}