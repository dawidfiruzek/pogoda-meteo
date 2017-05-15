package pl.floware.pogodameteo.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import pl.floware.pogodameteo.util.injection.AppComponent
import java.lang.ClassCastException

abstract class BaseFragment : Fragment() {

    lateinit var unbinder: Unbinder

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutId(), container, false)
        unbinder = ButterKnife.bind(this, view)

        initDaggerComponent()
        init()
        return view
    }

    protected abstract fun layoutId(): Int

    protected open fun initDaggerComponent() {}

    protected open fun init() {}
    //endregion

    override fun onDestroyView() {
        super.onDestroyView()
        clear()
        unbinder.unbind()
    }

    protected open fun clear() {}

    protected fun appComponent(): AppComponent {
        try {
            return (activity as BaseActivity).appComponent()
        } catch (e: ClassCastException) {
            throw ClassCastException("Activity has to extend " + BaseActivity::class.java.simpleName)
        }

    }
}
