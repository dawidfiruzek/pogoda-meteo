package pl.floware.pogodameteo.util.interactor

import android.location.LocationManager
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import pl.floware.pogodameteo.BaseTest

class LocationInteractorTest : BaseTest() {

    @Mock
    private lateinit var locationManager: LocationManager

    @Mock
    private lateinit var androidLocation: android.location.Location

    private lateinit var interactor: LocationInteractor

    override fun setup() {
        super.setup()

        interactor = LocationInteractorImpl(locationManager)

        `when`(locationManager.getLastKnownLocation(anyString())).thenReturn(androidLocation)
    }

    @Test
    fun interactorTest() {
        interactor.locationObservable()
                .test()
                .assertNoErrors()

    }
}