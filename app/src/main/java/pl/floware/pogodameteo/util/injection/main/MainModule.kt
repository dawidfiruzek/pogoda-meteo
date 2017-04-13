package pl.floware.pogodameteo.util.injection.main

import dagger.Module
import dagger.Provides
import pl.floware.pogodameteo.ui.main.MainContract
import pl.floware.pogodameteo.ui.main.MainPresenter

@Module
class MainModule {

    @Provides
    fun provideMainPresenter(): MainContract.Presenter = MainPresenter()
}
