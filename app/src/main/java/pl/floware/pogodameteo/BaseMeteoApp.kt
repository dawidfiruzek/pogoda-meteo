package pl.floware.pogodameteo

import android.app.Application
import pl.floware.pogodameteo.util.injection.app.AppComponent
import pl.floware.pogodameteo.util.injection.app.AppModule
import pl.floware.pogodameteo.util.injection.app.DaggerAppComponent

abstract class BaseMeteoApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        init()
    }

    protected open fun init() {
        initAppComponent()
        initTimber()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    protected abstract fun initTimber()
}
