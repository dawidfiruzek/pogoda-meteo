package pl.floware.pogodameteo.ui.main

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import pl.floware.pogodameteo.ui.BasePresenter
import timber.log.Timber

class MainPresenter(
        val mainInteractor: MainInteractor,
        val compositeDisposable: CompositeDisposable)
    : BasePresenter<MainContract.View, MainContract.Router>(), MainContract.Presenter {

    override fun initBindings() {
        val weather: Observable<MainViewModel> = getDeferObservable { view?.getWeatherClickedIntent() }
                .doOnNext { Timber.i("weather clicked") }
                .flatMap { mainInteractor.getWeatherObservable() }
                .onErrorReturn { MainViewModel.getErrorModel() }
        val comment: Observable<MainViewModel> = getDeferObservable { view?.getCommentClickedIntent() }
                .doOnNext { Timber.i("comment clicked") }
                .flatMap{ mainInteractor.getCommentObservable() }
                .onErrorReturn { MainViewModel.getErrorModel() }
        val settings: Observable<MainViewModel> = getDeferObservable { view?.getSettingsClickedIntent() }
                .doOnNext { Timber.i("settings clicked") }
                .flatMap { mainInteractor.getSettingsObservable() }
                .onErrorReturn { MainViewModel.getErrorModel() }

        compositeDisposable.add(Observable.merge(weather, comment, settings)
                .subscribe(
                        { Timber.d(it.toString()) },
                        { Timber.e(it) }))

        //todo add state reducer with init state
    }

    override fun clear() {
        compositeDisposable.clear()
        super.clear()
    }
}
