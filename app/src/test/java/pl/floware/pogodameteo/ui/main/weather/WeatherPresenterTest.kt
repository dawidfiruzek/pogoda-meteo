package pl.floware.pogodameteo.ui.main.weather

import io.reactivex.subjects.PublishSubject
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import pl.floware.pogodameteo.BaseTest
import pl.floware.pogodameteo.util.interactor.ImageInteractor

class WeatherPresenterTest : BaseTest() {

    @Mock
    private lateinit var view: WeatherContract.View

    @Mock
    private lateinit var interactor: ImageInteractor

    @Mock
    private lateinit var router: WeatherContract.Router

    private lateinit var presenter: WeatherContract.Presenter

    private lateinit var refreshObservable: PublishSubject<Any>
    private lateinit var imageObservable: PublishSubject<String>

    override fun setup() {
        super.setup()
        trampolineRxPlugin()

        presenter = WeatherPresenter(interactor, compositeDisposable)
        presenter.attachView(view)
        presenter.attachRouter(router)

        refreshObservable = PublishSubject.create()
        imageObservable = PublishSubject.create()

        Mockito.`when`(view.getRefreshObservable()).thenReturn(refreshObservable)
        Mockito.`when`(interactor.imageObservable()).thenReturn(imageObservable)

        presenter.initBindings()

        Mockito.verify(view, Mockito.times(1)).getRefreshObservable()
    }

    override fun tearDown() {
        super.tearDown()

        presenter.clear()
        Mockito.verifyNoMoreInteractions(view, router)
    }

    @Test
    fun refresh_showImage() {
        val url = "test"
        refreshObservable.onNext(true)
        imageObservable.onNext(url)
        Mockito.verify(view, Mockito.times(1)).showImage(url)
    }

    @Test
    fun refresh_refreshError() {
        refreshObservable.onError(Exception())
        imageObservable.onNext("test")
        Mockito.verify(view, Mockito.times(1)).showImage(WeatherModel.errorUrl)
    }

    @Test
    fun refresh_imageError() {
        refreshObservable.onNext(true)
        imageObservable.onError(Exception())
        Mockito.verify(view, Mockito.times(1)).showImage(WeatherModel.errorUrl)
    }
}