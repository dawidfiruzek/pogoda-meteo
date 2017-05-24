package pl.floware.pogodameteo.util.interactor

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit

class ImageInteractorImpl : ImageInteractor {

    override fun imageObservable(weatherUrl: String): Observable<String> {
        return Observable.zip(
                Observable.interval(0, 3000, TimeUnit.MILLISECONDS),
                Observable.fromIterable(arrayListOf(
                        "https://s-media-cache-ak0.pinimg.com/736x/3d/2b/bf/3d2bbfd73ccaf488ab88d298ab7bc2d8.jpg",
                        "https://cdn.thinglink.me/api/image/727110550026190849/1240/10/scaletowidth",
                        "http://img15.deviantart.net/97dd/i/2013/229/d/f/doge_squadron_by_shankidy-d6ijgat.png",
                        "https://s-media-cache-ak0.pinimg.com/originals/ee/b7/71/eeb771122eebac79dda2eb99d43e3d82.jpg",
                        "https://s-media-cache-ak0.pinimg.com/originals/24/a0/9e/24a09e49522b48d0f490de86b5d624da.jpg",
                        "https://s-media-cache-ak0.pinimg.com/736x/55/f9/25/55f92575f958c607cf0467274d6b7539.jpg",
                        "https://s-media-cache-ak0.pinimg.com/736x/b1/f0/7a/b1f07adfb3fb75f193b0762db7e8b090.jpg",
                        "https://sites.google.com/a/suffieldstudent.org/doges-com/_/rsrc/1472846231221/types-of-doges/CORN%20DOGE.jpg?height=215&width=400")),
                BiFunction { _, image -> image }
        )
    }
}