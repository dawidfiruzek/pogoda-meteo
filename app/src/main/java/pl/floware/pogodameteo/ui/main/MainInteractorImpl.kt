package pl.floware.pogodameteo.ui.main

import io.reactivex.Observable

class MainInteractorImpl : MainInteractor {

    override fun getWeatherObservable(): Observable<MainViewModel> {
        return Observable.just(MainViewModel.getWeatherModel(meteogramUrl = "test"))
    }

    override fun getCommentObservable(): Observable<MainViewModel> {
        return Observable.just(MainViewModel.getCommentModel())
    }

    override fun getSettingsObservable(): Observable<MainViewModel> {
        return Observable.just(MainViewModel.getSettingsModel())
    }
}
