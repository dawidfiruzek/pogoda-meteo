package pl.floware.pogodameteo.ui.main

import pl.floware.pogodameteo.ui.BaseContract

interface MainContract {

    interface View : BaseContract.View

    interface Router : BaseContract.Router

    interface Presenter : BaseContract.Presenter<View, Router>
}
