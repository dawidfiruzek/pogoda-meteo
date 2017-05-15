package pl.floware.pogodameteo

import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

abstract class BaseTest {

    @Mock
    protected lateinit var compositeDisposable: CompositeDisposable

    @Before
    open fun setup() = MockitoAnnotations.initMocks(this)
}