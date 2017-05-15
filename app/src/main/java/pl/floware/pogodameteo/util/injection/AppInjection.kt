package pl.floware.pogodameteo.util.injection

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
import javax.inject.Singleton

@Singleton
@Component(
        modules = arrayOf(AppModule::class)
)
interface AppComponent {

    //region AppModule
    fun getConfiguration(): Configuration
    fun getResourceProvider(): ResourceProvider
    fun getCompositeDisposable(): CompositeDisposable
    //endregion
}

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
