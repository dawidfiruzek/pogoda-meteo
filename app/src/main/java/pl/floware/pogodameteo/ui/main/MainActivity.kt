package pl.floware.pogodameteo.ui.main

import pl.dawidfiruzek.template.util.injection.main.DaggerMainComponent
import pl.floware.pogodameteo.R
import pl.floware.pogodameteo.ui.BaseActivity
import pl.floware.pogodameteo.util.injection.main.MainModule
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View, MainContract.Router {

    @Inject
    lateinit var presenter: MainContract.Presenter

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
    }

    override fun clear() {
        presenter.clear()
    }
}
