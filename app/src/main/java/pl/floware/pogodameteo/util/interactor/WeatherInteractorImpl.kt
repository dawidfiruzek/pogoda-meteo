package pl.floware.pogodameteo.util.interactor

import android.net.Uri
import io.reactivex.Observable
import pl.floware.pogodameteo.data.Location
import timber.log.Timber

class WeatherInteractorImpl : WeatherInteractor {

    override fun weatherObservable(location: Location): Observable<Uri> =
        Observable.fromCallable { weatherUri(location) }

    private fun weatherUri(location: Location): Uri {
        val uri = Uri.Builder()
                .scheme("http")
                .authority("www.meteo.pl")
                .appendPath("um")
                .appendPath("php")
                .appendPath("mgram_search.php")
                .appendQueryParameter("NALL", location.latitude.toString())
                .appendQueryParameter("EALL", location.longitude.toString())
                .build()

        Timber.d(uri.toString())
        return uri
    }
}