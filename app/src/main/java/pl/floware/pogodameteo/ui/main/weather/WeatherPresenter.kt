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
                .subscribe({
                    showImage(it)
                }, {
                    Timber.e(it)
                }))
    }

    private fun showImage(image: String) {
        Timber.d(image)
        view?.showImage(image)
    }

    override fun clear() {
        super.clear()
        compositeDisposable.clear()
    }
}