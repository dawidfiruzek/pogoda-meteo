package pl.floware.pogodameteo.util.interactor

import io.reactivex.Observable
import pl.floware.pogodameteo.data.Location

interface LocationInteractor {
    fun locationObservable(): Observable<Location>
}