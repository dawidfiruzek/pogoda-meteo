package pl.floware.pogodameteo.util.configuration

import android.content.Context

class ResourceProviderImpl(val appContext: Context) : ResourceProvider {

    override fun getString(stringResId: Int): String = appContext.getString(stringResId)
}