package pl.floware.pogodameteo.ui

interface BaseContract {

    interface View

    interface Router

    interface Presenter<in V : View, in R : Router> {
        fun attachView(view: V)
        fun attachRouter(router: R)
        fun detachView()
        fun detachRouter()
        fun initBindings()
        fun clear()
    }
}
