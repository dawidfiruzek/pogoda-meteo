package pl.floware.pogodameteo.util.injection.app

import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import pl.floware.pogodameteo.BaseMeteoApp
import pl.floware.pogodameteo.util.configuration.Configuration
import pl.floware.pogodameteo.util.configuration.ConfigurationImpl
import pl.floware.pogodameteo.util.configuration.ResourceProvider
import pl.floware.pogodameteo.util.configuration.ResourceProviderImpl

@Module
class AppModule(val application: BaseMeteoApp) {

    @Provides
    fun providePreferences(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)

    @Provides
    fun provideConfiguration(sharedPreferences: SharedPreferences): Configuration = ConfigurationImpl(sharedPreferences)

    @Provides
    fun provideResourceProvider(): ResourceProvider = ResourceProviderImpl(application)

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}