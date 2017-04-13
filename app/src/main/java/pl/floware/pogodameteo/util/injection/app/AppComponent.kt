package pl.floware.pogodameteo.util.injection.app

import dagger.Component
import pl.floware.pogodameteo.util.configuration.Configuration
import pl.floware.pogodameteo.util.configuration.ResourceProvider
import javax.inject.Singleton

@Singleton
@Component(
        modules = arrayOf(
                AppModule::class
        )
)
interface AppComponent {

    //region AppModule
    fun getConfiguration(): Configuration
    fun getResourceProvider(): ResourceProvider
    //endregion
}