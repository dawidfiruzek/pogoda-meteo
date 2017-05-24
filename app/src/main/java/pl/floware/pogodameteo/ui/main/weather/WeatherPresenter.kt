package pl.floware.pogodameteo.ui.main.weather

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import pl.floware.pogodameteo.ui.BasePresenter
import pl.floware.pogodameteo.util.interactor.ImageInteractor
import pl.floware.pogodameteo.util.interactor.LocationInteractor
import pl.floware.pogodameteo.util.interactor.WeatherInteractor
import timber.log.Timber

class WeatherPresenter(
        val imageInteractor: ImageInteractor,
        val locationInteractor: LocationInteractor,
        val weatherInteractor: WeatherInteractor,
        val compositeDisposable: CompositeDisposable)
    : BasePresenter<WeatherContract.View, WeatherContract.Router>(), WeatherContract.Presenter{

    override fun initBindings() {
        val weather = deferObservable { view?.getRefreshObservable() }
                .flatMap { locationInteractor.locationObservable() }
                .flatMap { weatherInteractor.weatherObservable(it) }
                .flatMap { imageInteractor.imageObservable(it) }
                .map { WeatherModel.successWeatherModel(it) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())

        compositeDisposable.add(weather
                .onErrorReturn {
                    Timber.e(it)
                    WeatherModel.errorWeatherModel()
                }
                .subscribe {
                    showImage(it)
                })
    }

    private fun showImage(weatherModel: WeatherModel) {
        Timber.d(weatherModel.toString())
        view?.showImage(weatherModel.photoUrl)
    }

    override fun clear() {
        super.clear()
        compositeDisposable.clear()
    }
}