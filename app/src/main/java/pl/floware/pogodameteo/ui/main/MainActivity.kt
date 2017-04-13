package pl.floware.pogodameteo.ui.main

import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import butterknife.BindView
import com.jakewharton.rxbinding2.support.design.widget.RxBottomNavigationView
import io.reactivex.Observable
import pl.dawidfiruzek.template.util.injection.main.DaggerMainComponent
import pl.floware.pogodameteo.R
import pl.floware.pogodameteo.ui.BaseActivity
import pl.floware.pogodameteo.util.injection.main.MainModule
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View, MainContract.Router {

    @Inject
    lateinit var presenter: MainContract.Presenter

    @BindView(R.id.main_bottom_navigation)
    lateinit var bottomNavigationView: BottomNavigationView

    private val bottomNavigationObservable: Observable<MenuItem> =
        Observable.defer { RxBottomNavigationView.itemSelections(bottomNavigationView) }.share()

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initDaggerComponent() {
        DaggerMainComponent.builder()
                .appComponent(getAppComponent())
                .mainModule(MainModule())
                .build()
                .inject(this)
    }

    override fun init() {
        presenter.attachView(this)
        presenter.attachRouter(this)
        presenter.init()
    }

    override fun clear() {
        presenter.clear()
    }

    override fun getWeatherClickedIntent(): Observable<Boolean> {
        return bottomNavigationObservable
                .map { it.itemId == R.id.bottom_navigation_weather }
                .filter { it == true }
    }

    override fun getCommentClickedIntent(): Observable<Boolean> {
        return bottomNavigationObservable
                .map { it.itemId == R.id.bottom_navigation_comment }
                .filter { it == true }
    }

    override fun getSettingsClickedIntent(): Observable<Boolean> {
        return bottomNavigationObservable
                .map { it.itemId == R.id.bottom_navigation_settings }
                .filter { it == true }
    }
}
