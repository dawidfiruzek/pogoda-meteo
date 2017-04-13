package pl.dawidfiruzek.template.util.injection.main

import dagger.Component
import pl.floware.pogodameteo.ui.main.MainActivity
import pl.floware.pogodameteo.util.injection.app.AppComponent
import pl.floware.pogodameteo.util.injection.main.MainModule
import pl.floware.pogodameteo.util.injection.main.MainScope

@MainScope
@Component(
        dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(MainModule::class)
)
interface MainComponent {

    fun inject(activity: MainActivity)
}