package pl.floware.pogodameteo.ui

import io.reactivex.Observable

abstract class BasePresenter<V : BaseContract.View, R : BaseContract.Router> : BaseContract.Presenter<V, R> {

    protected var view: V? = null
    protected var router: R? = null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun attachRouter(router: R) {
        this.router = router
    }

    override fun detachView() {
        view = null
    }

    override fun detachRouter() {
        router = null
    }

    override fun clear() {
        detachView()
        detachRouter()
    }

    fun <T> getDeferObservable(observable: () -> Observable<T>?): Observable<T> = Observable.defer { observable() }
}
