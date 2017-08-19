package pl.floware.pogodameteo.ui.main.weather

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
    private lateinit var location: Location

    private lateinit var presenter: WeatherContract.Presenter

    private lateinit var refreshObservable: PublishSubject<Any>

    private lateinit var imageObservable: PublishSubject<String>
    private lateinit var locationObservable: PublishSubject<Location>
    private lateinit var weatherObservable: PublishSubject<String>

    private lateinit var weatherUrl: String
    private lateinit var imageUrl: String

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

        weatherUrl = "weather"
        imageUrl = "test"

        refreshObservable = PublishSubject.create()
        imageObservable = PublishSubject.create()
        locationObservable = PublishSubject.create()
        weatherObservable = PublishSubject.create()

        `when`(view.getRefreshObservable()).thenReturn(refreshObservable)
        `when`(locationIteractor.locationObservable()).thenReturn(locationObservable)
        `when`(weatherInteractor.weatherObservable(any(Location::class.java))).thenReturn(weatherObservable)
        `when`(imageInteractor.imageObservable(any(String::class.java))).thenReturn(imageObservable)

        presenter.initBindings()

        Assert.assertTrue(imageUrl != WeatherModel.errorUrl)
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
        weatherObservable.onNext(weatherUrl)
        imageObservable.onNext(imageUrl)
        verify(view, times(1)).showImage(imageUrl)
    }

    @Test
    fun refresh_refreshError() {
        refreshObservable.onError(Exception())
        locationObservable.onNext(location)
        weatherObservable.onNext(weatherUrl)
        imageObservable.onNext(imageUrl)
        verify(view, times(1)).showImage(WeatherModel.errorUrl)
    }

    @Test
    fun refresh_refreshLocationError() {
        refreshObservable.onError(Exception())
        locationObservable.onError(Exception())
        weatherObservable.onNext(weatherUrl)
        imageObservable.onNext(imageUrl)
        verify(view, times(1)).showImage(WeatherModel.errorUrl)
    }

    @Test
    fun refresh_refreshWeatherError() {
        refreshObservable.onError(Exception())
        locationObservable.onNext(location)
        weatherObservable.onError(Exception())
        imageObservable.onNext(imageUrl)
        verify(view, times(1)).showImage(WeatherModel.errorUrl)
    }

    @Test
    fun refresh_refreshImageError() {
        refreshObservable.onError(Exception())
        locationObservable.onNext(location)
        weatherObservable.onNext(weatherUrl)
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
        imageObservable.onNext(imageUrl)
        verify(view, times(1)).showImage(WeatherModel.errorUrl)
    }

    @Test
    fun refresh_refreshLocationImageError() {
        refreshObservable.onError(Exception())
        locationObservable.onError(Exception())
        weatherObservable.onNext(weatherUrl)
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
        weatherObservable.onNext(weatherUrl)
        imageObservable.onNext(imageUrl)
        verify(view, times(1)).showImage(WeatherModel.errorUrl)
    }

    @Test
    fun refresh_locationWeatherError() {
        refreshObservable.onNext(true)
        locationObservable.onError(Exception())
        weatherObservable.onError(Exception())
        imageObservable.onNext(imageUrl)
        verify(view, times(1)).showImage(WeatherModel.errorUrl)
    }

    @Test
    fun refresh_locationImageError() {
        refreshObservable.onNext(true)
        locationObservable.onError(Exception())
        weatherObservable.onNext(weatherUrl)
        imageObservable.onError(Exception())
        verify(view, times(1)).showImage(WeatherModel.errorUrl)
    }

    @Test
    fun refresh_weatherError() {
        refreshObservable.onNext(true)
        locationObservable.onNext(location)
        weatherObservable.onError(Exception())
        imageObservable.onNext(imageUrl)
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
        weatherObservable.onNext(weatherUrl)
        imageObservable.onError(Exception())
        verify(view, times(1)).showImage(WeatherModel.errorUrl)
    }
}