package pl.floware.pogodameteo.ui.main.weather

import android.support.v4.widget.SwipeRefreshLayout
import android.widget.ImageView
import butterknife.BindView
import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
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

    @BindView(R.id.weather_refresh)
    lateinit var swipeToRefresh: SwipeRefreshLayout

    @Inject
    lateinit var presenter: WeatherContract.Presenter

    @Inject
    lateinit var publishSubject: PublishSubject<Boolean>

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

    override fun onResume() {
        super.onResume()
        publishSubject.onNext(true)
    }

    override fun clear() {
        super.clear()
        presenter.clear()
        Picasso.with(context)
                .cancelRequest(image)
    }

    //region View
    override fun getRefreshObservable(): Observable<Any> =
            Observable.merge(publishSubject, RxSwipeRefreshLayout.refreshes(swipeToRefresh))

    override fun showImage(url: String) {
        Picasso.with(context)
                .load(url)
                .into(image, object : Callback {
                    override fun onSuccess() {
                        swipeToRefresh.isRefreshing = false
                    }

                    override fun onError() {
                        swipeToRefresh.isRefreshing = false
                    }
                })
    }
    //endregion
}
