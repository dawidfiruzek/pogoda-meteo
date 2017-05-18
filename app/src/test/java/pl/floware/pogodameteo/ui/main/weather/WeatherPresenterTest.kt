package pl.floware.pogodameteo.ui.main.weather

import io.reactivex.subjects.PublishSubject
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
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

    private lateinit var clicksObservable: PublishSubject<Any>
    private lateinit var dogeObservable: PublishSubject<String>

    override fun setup() {
        super.setup()
        trampolineRxPlugin()

        presenter = WeatherPresenter(interactor, compositeDisposable)
        presenter.attachView(view)
        presenter.attachRouter(router)

        clicksObservable = PublishSubject.create()
        dogeObservable = PublishSubject.create()

        Mockito.`when`(view.getButtonClickedObservable()).thenReturn(clicksObservable)
        Mockito.`when`(interactor.imageObservable()).thenReturn(dogeObservable)

        presenter.initBindings()

        Mockito.verify(view, Mockito.times(1)).getButtonClickedObservable()
    }

    override fun tearDown() {
        super.tearDown()

        presenter.clear()
        Mockito.verifyNoMoreInteractions(view, router)
    }

    @Test
    fun buttonClicked_showDoges() {
        clicksObservable.onNext(true)
        dogeObservable.onNext("test")
        Mockito.verify(view, Mockito.times(1)).showImage(anyString())
    }

    @Test
    fun buttonClicked_clickError() {
        clicksObservable.onError(Exception())
        dogeObservable.onNext("test")
        Mockito.verify(view, Mockito.times(1)).showImage(WeatherModel.errorUrl)
    }

    @Test
    fun buttonClicked_dogeError() {
        clicksObservable.onNext(true)
        dogeObservable.onError(Exception())
        Mockito.verify(view, Mockito.times(1)).showImage(WeatherModel.errorUrl)
    }
}