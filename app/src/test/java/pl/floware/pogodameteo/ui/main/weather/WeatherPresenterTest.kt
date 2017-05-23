package pl.floware.pogodameteo.ui.main.weather

import android.net.Uri
import io.reactivex.subjects.PublishSubject
import org.junit.Assert
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import pl.floware.pogodameteo.BaseTest
import pl.floware.pogodameteo.data.Location
import pl.floware.pogodameteo.util.interactor.ImageInteractor
import pl.floware.pogodameteo.util.interactor.LocationInteractor
import pl.floware.pogodameteo.util.interactor.WeatherInteractor

class WeatherPresenterTest : BaseTest() {

    @Mock
    private lateinit var view: WeatherContract.View

    @Mock
    private lateinit var router: WeatherContract.Router

    @Mock
    private lateinit var imageInteractor: ImageInteractor

    @Mock
    private lateinit var locationIteractor: LocationInteractor

    @Mock
    private lateinit var weatherInteractor: WeatherInteractor

    @Mock
    private lateinit var weatherUri: Uri

    @Mock
    private lateinit var location: Location

    private lateinit var presenter: WeatherContract.Presenter

    private lateinit var refreshObservable: PublishSubject<Any>
    private lateinit var imageObservable: PublishSubject<String>
    private lateinit var locationObservable: PublishSubject<Location>
    private lateinit var weatherObservable: PublishSubject<Uri>

    private lateinit var url: String

    override fun setup() {
        super.setup()
        trampolineRxPlugin()

        presenter = WeatherPresenter(
                imageInteractor = imageInteractor,
                locationInteractor = locationIteractor,
                weatherInteractor = weatherInteractor,
                compositeDisposable = compositeDisposable)
        presenter.attachView(view)
        presenter.attachRouter(router)

        url = "test"

        refreshObservable = PublishSubject.create()
        imageObservable = PublishSubject.create()
        locationObservable = PublishSubject.create()

        `when`(view.getRefreshObservable()).thenReturn(refreshObservable)
        `when`(imageInteractor.imageObservable()).thenReturn(imageObservable)
        `when`(locationIteractor.locationObservable()).thenReturn(locationObservable)
        `when`(weatherInteractor.weatherObservable(any())).thenReturn(weatherObservable)

        presenter.initBindings()

        Assert.assertTrue(url != WeatherModel.errorUrl)
        verify(view, times(1)).getRefreshObservable()
    }

    override fun tearDown() {
        super.tearDown()

        presenter.clear()
        verifyNoMoreInteractions(view, router)
    }

    @Test
    fun refresh_showImage() {
        refreshObservable.onNext(true)
        locationObservable.onNext(location)
        weatherObservable.onNext(weatherUri)
        imageObservable.onNext(url)
        verify(view, times(1)).showImage(url)
    }

    @Test
    fun refresh_refreshError() {
        refreshObservable.onError(Exception())
        locationObservable.onNext(location)
        weatherObservable.onNext(weatherUri)
        imageObservable.onNext(url)
        verify(view, times(1)).showImage(WeatherModel.errorUrl)
    }

    @Test
    fun refresh_refreshLocationError() {
        refreshObservable.onError(Exception())
        locationObservable.onError(Exception())
        weatherObservable.onNext(weatherUri)
        imageObservable.onNext(url)
        verify(view, times(1)).showImage(WeatherModel.errorUrl)
    }

    @Test
    fun refresh_refreshWeatherError() {
        refreshObservable.onError(Exception())
        locationObservable.onNext(location)
        weatherObservable.onError(Exception())
        imageObservable.onNext(url)
        verify(view, times(1)).showImage(WeatherModel.errorUrl)
    }

    @Test
    fun refresh_refreshImageError() {
        refreshObservable.onError(Exception())
        locationObservable.onNext(location)
        weatherObservable.onNext(weatherUri)
        imageObservable.onError(Exception())
        verify(view, times(1)).showImage(WeatherModel.errorUrl)
    }

    @Test
    fun refresh_refreshAllError() {
        refreshObservable.onError(Exception())
        locationObservable.onError(Exception())
        weatherObservable.onError(Exception())
        imageObservable.onError(Exception())
        verify(view, times(1)).showImage(WeatherModel.errorUrl)
    }

    @Test
    fun refresh_refreshLocationWeatherError() {
        refreshObservable.onError(Exception())
        locationObservable.onError(Exception())
        weatherObservable.onError(Exception())
        imageObservable.onNext(url)
        verify(view, times(1)).showImage(WeatherModel.errorUrl)
    }

    @Test
    fun refresh_refreshLocationImageError() {
        refreshObservable.onError(Exception())
        locationObservable.onError(Exception())
        weatherObservable.onNext(weatherUri)
        imageObservable.onError(Exception())
        verify(view, times(1)).showImage(WeatherModel.errorUrl)
    }

    @Test
    fun refresh_refreshWeatherImageError() {
        refreshObservable.onError(Exception())
        locationObservable.onNext(location)
        weatherObservable.onError(Exception())
        imageObservable.onError(Exception())
        verify(view, times(1)).showImage(WeatherModel.errorUrl)
    }

    @Test
    fun refresh_locationWeatherImageError() {
        refreshObservable.onNext(true)
        locationObservable.onError(Exception())
        weatherObservable.onError(Exception())
        imageObservable.onError(Exception())
        verify(view, times(1)).showImage(WeatherModel.errorUrl)
    }

    @Test
    fun refresh_locationError() {
        refreshObservable.onNext(true)
        locationObservable.onError(Exception())
        weatherObservable.onNext(weatherUri)
        imageObservable.onNext(url)
        verify(view, times(1)).showImage(WeatherModel.errorUrl)
    }

    @Test
    fun refresh_locationWeatherError() {
        refreshObservable.onNext(true)
        locationObservable.onError(Exception())
        weatherObservable.onError(Exception())
        imageObservable.onNext(url)
        verify(view, times(1)).showImage(WeatherModel.errorUrl)
    }

    @Test
    fun refresh_locationImageError() {
        refreshObservable.onNext(true)
        locationObservable.onError(Exception())
        weatherObservable.onNext(weatherUri)
        imageObservable.onError(Exception())
        verify(view, times(1)).showImage(WeatherModel.errorUrl)
    }

    @Test
    fun refresh_weatherError() {
        refreshObservable.onNext(true)
        locationObservable.onNext(location)
        weatherObservable.onError(Exception())
        imageObservable.onNext(url)
        verify(view, times(1)).showImage(WeatherModel.errorUrl)
    }

    @Test
    fun refresh_weatherImageError() {
        refreshObservable.onNext(true)
        locationObservable.onNext(location)
        weatherObservable.onError(Exception())
        imageObservable.onError(Exception())
        verify(view, times(1)).showImage(WeatherModel.errorUrl)
    }

    @Test
    fun refresh_imageError() {
        refreshObservable.onNext(true)
        locationObservable.onNext(location)
        weatherObservable.onNext(weatherUri)
        imageObservable.onError(Exception())
        verify(view, times(1)).showImage(WeatherModel.errorUrl)
    }
}