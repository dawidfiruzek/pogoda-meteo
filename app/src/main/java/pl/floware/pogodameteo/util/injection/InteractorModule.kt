package pl.floware.pogodameteo.util.injection

import dagger.Module
import dagger.Provides
import pl.floware.pogodameteo.util.interactor.ImageInteractor
import pl.floware.pogodameteo.util.interactor.ImageInteractorImpl
import pl.floware.pogodameteo.util.interactor.LocationInteractor
import pl.floware.pogodameteo.util.interactor.LocationInteractorImpl
import javax.inject.Singleton

@Module
class InteractorModule {

    @Singleton
    @Provides
    fun imageInteractor(): ImageInteractor = ImageInteractorImpl()

    @Singleton
    @Provides
    fun locationInteractor(): LocationInteractor = LocationInteractorImpl()
}