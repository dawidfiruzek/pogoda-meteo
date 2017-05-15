package pl.floware.pogodameteo.ui.main

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import pl.floware.pogodameteo.ui.BasePresenter
import timber.log.Timber

class MainPresenter(val compositeDisposable: CompositeDisposable)
    : BasePresenter<MainContract.View, MainContract.Router>(), MainContract.Presenter {

    override fun initBindings() {
        val weather: Observable<MainModel> = deferObservable { view?.weatherClickedObservable() }
                .doOnNext { Timber.i("weather clicked") }
                .map { MainModel.weatherMainModel() }
        val comment: Observable<MainModel> = deferObservable { view?.commentClickedObservable() }
                .doOnNext { Timber.i("comment clicked") }
                .map { MainModel.commentMainModel() }
        val settings: Observable<MainModel> = deferObservable { view?.settingsClickedObservable() }
                .doOnNext { Timber.i("settings clicked") }
                .map { MainModel.settingsMainModel() }

        compositeDisposable.add(
                Observable.merge(weather, comment, settings)
                        .onErrorReturn { MainModel.weatherMainModel() }
                        .subscribe({
                            Timber.d(it.toString())
                            showMainModel(it)
                        }, {
                            Timber.e(it)
                        })
        )
    }

    private fun showMainModel(model: MainModel) {
        when (model.mainModelElement) {
            MainModelElement.WEATHER -> view?.showWeather()
            MainModelElement.COMMENT -> view?.showComment()
            MainModelElement.SETTINGS -> view?.showSettings()
            else -> Timber.e("Invalid mainModel passed. Main model = %s", model)
        }
    }

    override fun clear() {
        super.clear()
        compositeDisposable.clear()
    }
}