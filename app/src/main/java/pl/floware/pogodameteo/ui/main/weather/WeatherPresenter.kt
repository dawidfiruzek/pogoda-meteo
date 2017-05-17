package pl.floware.pogodameteo.ui.main.weather

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import pl.floware.pogodameteo.ui.BasePresenter
import pl.floware.pogodameteo.util.interactor.ImageInteractor
import timber.log.Timber

class WeatherPresenter(val imageInteractor: ImageInteractor, val compositeDisposable: CompositeDisposable)
    : BasePresenter<WeatherContract.View, WeatherContract.Router>(), WeatherContract.Presenter{

    override fun initBindings() {
        val images = deferObservable { view?.getButtonClickedObservable() }
                .flatMap { imageInteractor.imageObservable() }
                .observeOn(AndroidSchedulers.mainThread())

        compositeDisposable.add(images
                .map { WeatherModel.successWeatherModel(it) }
                .onErrorReturn {
                    Timber.e(it)
                    WeatherModel.errorWeatherModel()
                }
                .subscribe({
                    showImage(it)
                }))
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