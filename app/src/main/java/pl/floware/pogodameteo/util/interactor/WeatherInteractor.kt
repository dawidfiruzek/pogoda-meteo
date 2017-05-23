package pl.floware.pogodameteo.util.interactor

import android.net.Uri
import io.reactivex.Observable
import pl.floware.pogodameteo.data.Location

interface WeatherInteractor {
    fun weatherObservable(location: Location): Observable<Uri>
}