package pl.floware.pogodameteo.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import pl.floware.pogodameteo.BaseMeteoApp
import pl.floware.pogodameteo.MeteoApp
import pl.floware.pogodameteo.util.injection.AppComponent
import java.lang.ClassCastException

abstract class BaseActivity : AppCompatActivity() {

    //region Init
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        ButterKnife.bind(this)

        initDaggerComponent()
        init()
    }

    protected abstract fun layoutId(): Int

    protected open fun initDaggerComponent() {}

    protected open fun init() {}
    //endregion

    override fun onDestroy() {
        clear()
        super.onDestroy()
    }

    protected open fun clear() {}

    fun appComponent(): AppComponent {
        try {
            return (application as MeteoApp).appComponent
        } catch (e: ClassCastException) {
            throw ClassCastException("Application has to extend " + BaseMeteoApp::class.java.simpleName)
        }

    }
}
