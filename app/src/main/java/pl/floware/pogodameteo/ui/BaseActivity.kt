package pl.floware.pogodameteo.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import pl.floware.pogodameteo.BaseMeteoApp
import pl.floware.pogodameteo.MeteoApp
import pl.floware.pogodameteo.util.injection.app.AppComponent
import java.lang.ClassCastException

abstract class BaseActivity : AppCompatActivity() {

    //region Init
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        ButterKnife.bind(this)

        initDaggerComponent()
        init()
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initDaggerComponent()

    protected abstract fun init()
    //endregion

    override fun onDestroy() {
        clear()
        super.onDestroy()
    }

    protected abstract fun clear()

    protected fun getAppComponent(): AppComponent {
        try {
            return (application as MeteoApp).appComponent
        } catch (e: ClassCastException) {
            throw ClassCastException("Application has to extends " + BaseMeteoApp::class.java.simpleName)
        }

    }
}
