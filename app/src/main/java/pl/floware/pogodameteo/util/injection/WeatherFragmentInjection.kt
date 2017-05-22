package pl.floware.pogodameteo.util.injection

import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import pl.floware.pogodameteo.ui.main.weather.WeatherContract
import pl.floware.pogodameteo.ui.main.weather.WeatherFragment
import pl.floware.pogodameteo.ui.main.weather.WeatherPresenter
import pl.floware.pogodameteo.util.interactor.ImageInteractor
import pl.floware.pogodameteo.util.interactor.LocationInteractor
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class WeatherFragmentScope

@WeatherFragmentScope
@Component (
        dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(WeatherFragmentModule::class)
)
interface WeatherFragmentComponent {
    fun inject(fragment: WeatherFragment)
}

@Module
class WeatherFragmentModule {

    @Provides
    fun presenter(imageInteractor: ImageInteractor,
                  locationInteractor: LocationInteractor,
                  compositeDisposable: CompositeDisposable): WeatherContract.Presenter
            = WeatherPresenter(imageInteractor, locationInteractor, compositeDisposable)

    @Provides
    fun publishSubject(): PublishSubject<Boolean> = PublishSubject.create()
}
