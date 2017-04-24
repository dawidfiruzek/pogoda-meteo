package pl.floware.pogodameteo.util.injection.main

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import pl.floware.pogodameteo.ui.main.MainContract
import pl.floware.pogodameteo.ui.main.MainInteractor
import pl.floware.pogodameteo.ui.main.MainInteractorImpl
import pl.floware.pogodameteo.ui.main.MainPresenter

@Module
class MainModule {

    @Provides
    fun provideMainInteractor(): MainInteractor = MainInteractorImpl()

    @Provides
    fun provideMainPresenter(
            mainInteractor: MainInteractor,
            compositeDisposable: CompositeDisposable): MainContract.Presenter {
        return MainPresenter(
                mainInteractor,
                compositeDisposable)
    }
}
