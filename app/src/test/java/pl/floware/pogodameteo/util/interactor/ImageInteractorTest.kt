package pl.floware.pogodameteo.util.interactor

import org.junit.Test
import pl.floware.pogodameteo.BaseTest

class ImageInteractorTest : BaseTest() {

    private lateinit var interactor: ImageInteractor

    private lateinit var weatherUrl: String

    override fun setup() {
        super.setup()

        interactor = ImageInteractorImpl()
        weatherUrl = "weather"
    }

    @Test
    fun interactorTest() {
        interactor.imageObservable(weatherUrl)
                .test()
                .assertNoErrors()
    }
}