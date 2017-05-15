package pl.floware.pogodameteo.ui.main.weather

import pl.floware.pogodameteo.R
import pl.floware.pogodameteo.ui.BaseFragment
import pl.floware.pogodameteo.util.injection.DaggerWeatherFragmentComponent
import pl.floware.pogodameteo.util.injection.WeatherFragmentModule
import javax.inject.Inject

class WeatherFragment : BaseFragment(), WeatherContract.View, WeatherContract.Router {

    companion object {
        val TAG: String = WeatherFragment::class.java.simpleName
        fun getInstance() = WeatherFragment()
    }

    @Inject
    lateinit var presenter: WeatherContract.Presenter

    override fun layoutId() = R.layout.fragment_weather

    override fun initDaggerComponent() {
        DaggerWeatherFragmentComponent.builder()
                .appComponent(appComponent())
                .weatherFragmentModule(WeatherFragmentModule())
                .build()
                .inject(this)
    }

    override fun init() {
        super.init()
        presenter.attachView(this)
        presenter.attachRouter(this)
        presenter.initBindings()
    }

    override fun clear() {
        super.clear()
        presenter.clear()
    }
}
