package pl.floware.pogodameteo.util.interactor

import io.reactivex.Observable

interface LocationInteractor {
    fun requestLocation()
    fun locationObservable(): Observable<Location>
}

data class Location(val latitude: Double, val longitude: Double)