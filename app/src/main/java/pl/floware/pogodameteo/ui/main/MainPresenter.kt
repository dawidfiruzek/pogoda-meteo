package pl.floware.pogodameteo.ui.main

import pl.floware.pogodameteo.ui.BasePresenter
import timber.log.Timber

class MainPresenter : BasePresenter<MainContract.View, MainContract.Router>(), MainContract.Presenter {

    override fun init() {
        view?.getWeatherClickedIntent()?.map { it == true }?.subscribe({ Timber.e("weather clicked") })
        view?.getCommentClickedIntent()?.map { it == true }?.subscribe({ Timber.e("comment clicked") })
        view?.getSettingsClickedIntent()?.map { it == true }?.subscribe({ Timber.e("settings clicked") })
        //todo init intents
    }
}
