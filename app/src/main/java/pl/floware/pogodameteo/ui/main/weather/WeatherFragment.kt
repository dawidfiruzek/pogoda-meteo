package pl.floware.pogodameteo.ui.main.weather

import android.widget.Button
import android.widget.ImageView
import butterknife.BindView
import com.jakewharton.rxbinding2.view.RxView
import com.squareup.picasso.Picasso
import io.reactivex.Observable
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

    @BindView(R.id.weather_image)
    lateinit var image: ImageView

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

    //region View
    override fun getRefreshObservable(): Observable<Any> = Observable.fromCallable { onResume() }

    override fun showImage(url: String) {
        Picasso.with(context)
                .load(url)
                .into(image)
    }
    //endregion
}
