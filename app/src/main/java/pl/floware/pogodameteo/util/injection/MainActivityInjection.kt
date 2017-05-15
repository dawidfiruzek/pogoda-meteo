package pl.floware.pogodameteo.util.injection

import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import pl.floware.pogodameteo.ui.main.MainActivity
import pl.floware.pogodameteo.ui.main.MainContract
import pl.floware.pogodameteo.ui.main.MainPresenter
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class MainActivityScope

@MainActivityScope
@Component(
        dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(MainActivityModule::class)
)
interface MainActivityComponent {
    fun inject(activity: MainActivity)
}

@Module
class MainActivityModule {

    @Provides
    fun providePresenter(compositeDisposable: CompositeDisposable): MainContract.Presenter = MainPresenter(compositeDisposable)
}
