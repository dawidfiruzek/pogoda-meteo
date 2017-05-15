package pl.floware.pogodameteo.ui.main

import pl.floware.pogodameteo.R
import pl.floware.pogodameteo.ui.BaseActivity
import pl.floware.pogodameteo.util.injection.DaggerMainActivityComponent
import pl.floware.pogodameteo.util.injection.MainActivityModule
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var presenter : MainContract.Presenter

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initDaggerComponent() {
        DaggerMainActivityComponent.builder()
                .appComponent(getAppComponent())
                .mainActivityModule(MainActivityModule())
                .build()
                .inject(this)
    }
}
