package pl.floware.pogodameteo.util.interactor

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit

class ImageInteractorImpl(val images: List<String>) : ImageInteractor {

    override fun imageObservable(): Observable<String> {
        return Observable.zip(
                Observable.interval(0, 3000, TimeUnit.MILLISECONDS),
                Observable.fromIterable(images),
                BiFunction { _, image -> image }
        )
    }
}