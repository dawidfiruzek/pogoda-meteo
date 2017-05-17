package pl.floware.pogodameteo.util.interactor

import io.reactivex.Observable

interface ImageInteractor {
    fun imageObservable(): Observable<String>
}