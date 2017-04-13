package pl.floware.pogodameteo.ui.main

import pl.floware.pogodameteo.ui.BasePresenter
import timber.log.Timber

class MainPresenter : BasePresenter<MainContract.View, MainContract.Router>(), MainContract.Presenter {

    override fun init() {
        view?.getWeatherClickedIntent()?.subscribe({ Timber.e("weather clicked") })
        view?.getCommentClickedIntent()?.subscribe({ Timber.e("comment clicked") })
        view?.getSettingsClickedIntent()?.subscribe({ Timber.e("settings clicked") })
        //todo add subscriptions to composite one
    }
}
