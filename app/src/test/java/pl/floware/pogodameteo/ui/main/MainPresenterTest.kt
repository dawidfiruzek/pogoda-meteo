package pl.floware.pogodameteo.ui.main

import io.reactivex.subjects.PublishSubject
import org.junit.After
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import pl.floware.pogodameteo.BaseTest

class MainPresenterTest : BaseTest() {

    @Mock
    private lateinit var view: MainContract.View

    @Mock
    private lateinit var router: MainContract.Router

    private lateinit var presenter: MainContract.Presenter

    private lateinit var weatherObservable: PublishSubject<Boolean>
    private lateinit var commentObservable: PublishSubject<Boolean>
    private lateinit var settingsObservable: PublishSubject<Boolean>

    override fun setup() {
        super.setup()
        presenter = MainPresenter(compositeDisposable)
        presenter.attachView(view)
        presenter.attachRouter(router)

        weatherObservable = PublishSubject.create()
        commentObservable = PublishSubject.create()
        settingsObservable = PublishSubject.create()

        `when`(view.weatherClickedObservable()).thenReturn(weatherObservable)
        `when`(view.commentClickedObservable()).thenReturn(commentObservable)
        `when`(view.settingsClickedObservable()).thenReturn(settingsObservable)

        presenter.initBindings()

        verify(view, times(1)).weatherClickedObservable()
        verify(view, times(1)).commentClickedObservable()
        verify(view, times(1)).settingsClickedObservable()
    }

    @After
    fun tearDown() {
        presenter.clear()
        verifyNoMoreInteractions(view, router)
    }


    @Test
    fun weatherClicked_showWeather() {
        weatherObservable.onNext(true)
        verify(view, times(1)).showWeather()
    }

    @Test
    fun commentClicked_showComment() {
        commentObservable.onNext(true)
        verify(view, times(1)).showComment()
    }

    @Test
    fun settingsClicked_showSettings() {
        settingsObservable.onNext(true)
        verify(view, times(1)).showSettings()
    }

    @Test
    fun weatherClicked_errorOccurred() {
        weatherObservable.onError(IllegalStateException())
        verify(view, times(1)).showWeather()
    }

    @Test
    fun commentClicked_errorOccurred() {
        commentObservable.onError(IllegalStateException())
        verify(view, times(1)).showWeather()
    }

    @Test
    fun settingsClicked_errorOccurred() {
        settingsObservable.onError(IllegalStateException())
        verify(view, times(1)).showWeather()
    }
}