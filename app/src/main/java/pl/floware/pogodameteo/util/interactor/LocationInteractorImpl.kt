package pl.floware.pogodameteo.util.interactor

import android.location.LocationManager
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class LocationInteractorImpl(val locationManager: LocationManager, val publishSubject: PublishSubject<Location>) : LocationInteractor {

    override fun locationObservable(): Observable<Location> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}