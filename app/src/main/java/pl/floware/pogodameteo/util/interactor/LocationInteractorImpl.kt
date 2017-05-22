package pl.floware.pogodameteo.util.interactor

import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import timber.log.Timber

class LocationInteractorImpl(val locationManager: LocationManager) : LocationInteractor {

    val observable: Observable<Location> = Observable.create { emitter: ObservableEmitter<Location> ->
        val locationListener = object : LocationListener {
            override fun onLocationChanged(location: android.location.Location) {
                val l = Location(location.latitude, location.longitude)
                Timber.d("Using new location $l")
                emitter.onNext(l)
                emitter.onComplete()
                locationManager.removeUpdates(this)
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

            override fun onProviderEnabled(provider: String?) {}

            override fun onProviderDisabled(provider: String?) = emitter.onError(Exception("LocationProvider is disabled"))
        }

        val location: android.location.Location? = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

        if (location != null) {
            val l = Location(location.latitude, location.longitude)
            Timber.d("Using last location $l")
            emitter.onNext(l)
            emitter.onComplete()
        } else if (networkEnabled()) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0F, locationListener)
        } else if (gpsEnabled()) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0F, locationListener)
        } else {
            emitter.onError(Exception("Failed to request location"))
        }
    }

    private fun networkEnabled() = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

    private fun gpsEnabled() = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

    override fun locationObservable(): Observable<Location> = observable
}