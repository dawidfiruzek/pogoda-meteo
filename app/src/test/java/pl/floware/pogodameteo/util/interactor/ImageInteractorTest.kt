package pl.floware.pogodameteo.util.interactor

import org.junit.Test
import pl.floware.pogodameteo.BaseTest

class ImageInteractorTest : BaseTest() {

    private lateinit var interactor: ImageInteractor

    override fun setup() {
        super.setup()

        interactor = ImageInteractorImpl()
    }

    @Test
    fun interactorTest() {
        interactor.imageObservable()
                .test()
                .assertNoErrors()
    }
}