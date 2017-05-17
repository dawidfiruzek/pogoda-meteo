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
import pl.floware.pogodameteo.util.interactor.ImageInteractor
import pl.floware.pogodameteo.util.interactor.ImageInteractorImpl
import javax.inject.Singleton

@Singleton
@Component(
        modules = arrayOf(AppModule::class)
)
interface AppComponent {

    //region AppModule
    fun configuration(): Configuration
    fun resourceProvider(): ResourceProvider
    fun compositeDisposable(): CompositeDisposable
    fun imageInteractor(): ImageInteractor
    //endregion
}

@Module
class AppModule(val application: BaseMeteoApp) {

    @Provides
    fun preferences(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)

    @Provides
    fun configuration(sharedPreferences: SharedPreferences): Configuration = ConfigurationImpl(sharedPreferences)

    @Provides
    fun resourceProvider(): ResourceProvider = ResourceProviderImpl(application)

    @Provides
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun imageInteractor(): ImageInteractor = ImageInteractorImpl(arrayListOf(
            "https://s-media-cache-ak0.pinimg.com/736x/3d/2b/bf/3d2bbfd73ccaf488ab88d298ab7bc2d8.jpg",
            "https://cdn.thinglink.me/api/image/727110550026190849/1240/10/scaletowidth",
            "http://img15.deviantart.net/97dd/i/2013/229/d/f/doge_squadron_by_shankidy-d6ijgat.png",
            "https://s-media-cache-ak0.pinimg.com/originals/ee/b7/71/eeb771122eebac79dda2eb99d43e3d82.jpg",
            "https://s-media-cache-ak0.pinimg.com/originals/24/a0/9e/24a09e49522b48d0f490de86b5d624da.jpg",
            "http://www.imgbase.info/images/safe-wallpapers/miscellaneous/funny/48666_funny_doge.jpg",
            "https://sites.google.com/a/suffieldstudent.org/doges-com/_/rsrc/1472846231221/types-of-doges/CORN%20DOGE.jpg?height=215&width=400"))
}
