package pl.floware.pogodameteo

import pl.floware.pogodameteo.util.ReleaseTree
import timber.log.Timber

class MeteoApp : BaseMeteoApp() {

    override fun initTimber() {
        Timber.plant(ReleaseTree())
    }
}
