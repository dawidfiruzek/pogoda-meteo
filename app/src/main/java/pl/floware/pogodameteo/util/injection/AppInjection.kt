package pl.floware.pogodameteo.util.injection

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import pl.floware.pogodameteo.BaseMeteoApp
import pl.floware.pogodameteo.util.configuration.Configuration
import pl.floware.pogodameteo.util.configuration.ConfigurationImpl
import pl.floware.pogodameteo.util.configuration.ResourceProvider
import pl.floware.pogodameteo.util.configuration.ResourceProviderImpl
import pl.floware.pogodameteo.util.interactor.ImageInteractor
import pl.floware.pogodameteo.util.interactor.ImageInteractorImpl
import pl.floware.pogodameteo.util.interactor.LocationInteractor
import pl.floware.pogodameteo.util.interactor.WeatherInteractor
import javax.inject.Singleton

@Singleton
@Component(
        modules = arrayOf(
                AppModule::class,
                InteractorModule::class)
)
interface AppComponent {

    //region AppModule
    fun configuration(): Configuration
    fun resourceProvider(): ResourceProvider
    fun compositeDisposable(): CompositeDisposable
    //endregion

    //region InteractorModule
    fun imageInteractor(): ImageInteractor
    fun locationInteractor(): LocationInteractor
    fun weatherInteractor(): WeatherInteractor
    //endregion
}

@Module
class AppModule(val application: BaseMeteoApp) {

    @Provides
    fun context(): Context = application

    @Provides
    fun preferences(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)

    @Provides
    fun configuration(sharedPreferences: SharedPreferences): Configuration = ConfigurationImpl(sharedPreferences)

    @Provides
    fun resourceProvider(): ResourceProvider = ResourceProviderImpl(application)

    @Provides
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()
}
