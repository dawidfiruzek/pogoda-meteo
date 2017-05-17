package pl.floware.pogodameteo.util.interactor

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class ImageInteractorImpl(val images: List<String>) : ImageInteractor {

    override fun imageObservable(): Observable<String> {
        return Observable.fromIterable(images)
                .timeout(2000, TimeUnit.MILLISECONDS)
    }
}