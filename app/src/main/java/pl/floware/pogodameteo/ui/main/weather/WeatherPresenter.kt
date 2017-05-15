package pl.floware.pogodameteo.ui.main.weather

import io.reactivex.disposables.CompositeDisposable
import pl.floware.pogodameteo.ui.BasePresenter

class WeatherPresenter(val compositeDisposable: CompositeDisposable)
    : BasePresenter<WeatherContract.View, WeatherContract.Router>(), WeatherContract.Presenter{

    override fun initBindings() {
        //todo init bindings
    }
}