package pl.floware.pogodameteo

import android.app.Application
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric
import pl.floware.pogodameteo.util.injection.AppComponent
import pl.floware.pogodameteo.util.injection.AppModule
import pl.floware.pogodameteo.util.injection.DaggerAppComponent

abstract class BaseMeteoApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        init()
    }

    protected open fun init() {
        initAppComponent()
        initTimber()
        initFabric()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    protected abstract fun initTimber()

    private fun initFabric() {
        if (BuildConfig.FLAVOR != "develop") {
            Fabric.with(this, Crashlytics())
        }
    }
}
