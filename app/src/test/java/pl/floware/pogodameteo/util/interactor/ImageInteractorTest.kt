package pl.floware.pogodameteo.util.interactor

import org.junit.Test
import pl.floware.pogodameteo.BaseTest

class ImageInteractorTest : BaseTest() {

    private lateinit var images: List<String>
    private lateinit var interactor: ImageInteractor

    override fun setup() {
        super.setup()

        images = listOf("test")
        interactor = ImageInteractorImpl(images)
    }

    @Test
    fun interactorTest() {
        interactor.imageObservable()
                .test()
                .assertResult(images[0])
    }
}