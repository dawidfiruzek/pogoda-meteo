package pl.floware.pogodameteo.util.interactor

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import pl.floware.pogodameteo.BaseTest
import pl.floware.pogodameteo.data.Location

class WeatherInteractorTest : BaseTest() {

    @Mock
    private lateinit var location: Location

    private lateinit var interactor: WeatherInteractor

    override fun setup() {
        super.setup()

        interactor = WeatherInteractorImpl()

        `when`(location.latitude).thenReturn(0.0)
        `when`(location.longitude).thenReturn(0.0)
    }

    @Test
    fun interactorTest() {
        interactor.weatherObservable(location)
                .test()
                .assertNoErrors()
    }
}