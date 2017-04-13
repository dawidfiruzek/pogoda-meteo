package pl.floware.pogodameteo.util.configuration

import android.support.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes stringResId: Int): String
}