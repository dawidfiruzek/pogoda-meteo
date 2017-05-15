package pl.floware.pogodameteo.ui.main

import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import android.view.View
import butterknife.BindView
import com.jakewharton.rxbinding2.support.design.widget.RxBottomNavigationView
import io.reactivex.Observable
import pl.floware.pogodameteo.R
import pl.floware.pogodameteo.ui.BaseActivity
import pl.floware.pogodameteo.util.injection.DaggerMainActivityComponent
import pl.floware.pogodameteo.util.injection.MainActivityModule
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View, MainContract.Router {

    @Inject
    lateinit var presenter : MainContract.Presenter

    @BindView(R.id.main_bottom_navigation)
    lateinit var bottomNavigationView: BottomNavigationView

    private val bottomNavigationObservable: Observable<MenuItem> =
            Observable.defer { RxBottomNavigationView.itemSelections(bottomNavigationView) }.share()

    override fun layoutId(): Int = R.layout.activity_main

    override fun initDaggerComponent() {
        DaggerMainActivityComponent.builder()
                .appComponent(appComponent())
                .mainActivityModule(MainActivityModule())
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

    override fun weatherClickedObservable(): Observable<Boolean> {
        return bottomNavigationObservable
                .map { it.itemId == R.id.bottom_navigation_weather }
                .filter { it == true }
    }

    override fun commentClickedObservable(): Observable<Boolean> {
        return bottomNavigationObservable
                .map { it.itemId == R.id.bottom_navigation_comment }
                .filter { it == true }
    }

    override fun settingsClickedObservable(): Observable<Boolean> {
        return bottomNavigationObservable
                .map { it.itemId == R.id.bottom_navigation_settings }
                .filter { it == true }
    }
}
