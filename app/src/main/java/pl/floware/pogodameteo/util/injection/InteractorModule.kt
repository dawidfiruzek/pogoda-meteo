package pl.floware.pogodameteo.util.injection

import android.content.Context
import android.location.LocationManager
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject
import pl.floware.pogodameteo.util.interactor.*
import javax.inject.Singleton

@Module
class InteractorModule {

    @Singleton
    @Provides
    fun imageInteractor(): ImageInteractor = ImageInteractorImpl()

    @Provides
    fun locationManager(appContext: Context): LocationManager = appContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    @Singleton
    @Provides
    fun locationInteractor(locationManager: LocationManager): LocationInteractor = LocationInteractorImpl(locationManager)
}