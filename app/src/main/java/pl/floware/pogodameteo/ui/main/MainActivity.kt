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

    private var bottomNavigationObservable: Observable<MenuItem>? = null

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
        if (bottomNavigationObservable == null) {
            initBottomNavigationObservable()
        }
        return bottomNavigationObservable!!.map { it.itemId == R.id.bottom_navigation_weather }
    }

    private fun initBottomNavigationObservable() {
        bottomNavigationObservable = RxBottomNavigationView.itemSelections(bottomNavigationView)
    }

    override fun getCommentClickedIntent(): Observable<Boolean> {
        if (bottomNavigationObservable == null) {
            initBottomNavigationObservable()
        }
        return bottomNavigationObservable!!.map { it.itemId == R.id.bottom_navigation_comment }
    }

    override fun getSettingsClickedIntent(): Observable<Boolean> {
        if (bottomNavigationObservable == null) {
            initBottomNavigationObservable()
        }
        return bottomNavigationObservable!!.map { it.itemId == R.id.bottom_navigation_settings }
    }

    override fun onResume() {
        super.onResume()
    }
}
