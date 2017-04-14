package pl.floware.pogodameteo.ui.main

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import pl.floware.pogodameteo.ui.BasePresenter
import timber.log.Timber

class MainPresenter(
        val compositeDisposable: CompositeDisposable)
    : BasePresenter<MainContract.View, MainContract.Router>(), MainContract.Presenter {

    override fun initBindings() {
        val weather: Observable<MainViewModel> = getDeferObservable { view?.getWeatherClickedIntent() }
                .map { MainViewModel() }
                .doOnNext { Timber.d("weather clicked") }
                .doOnError { Timber.e(it) }
        val comment: Observable<MainViewModel> = getDeferObservable { view?.getCommentClickedIntent() }
                .map { MainViewModel() }
                .doOnNext { Timber.d("comment clicked") }
                .doOnError { Timber.e(it) }
        val settings: Observable<MainViewModel> = getDeferObservable { view?.getSettingsClickedIntent() }
                .map { MainViewModel() }
                .doOnNext { Timber.d("settings clicked") }
                .doOnError { Timber.e(it) }

        compositeDisposable.add(Observable.merge(weather, comment, settings)
                .subscribe())
    }

    override fun clear() {
        compositeDisposable.clear()
        super.clear()
    }
}
