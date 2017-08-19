package pl.floware.pogodameteo.util.interactor

import android.net.Uri
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.util.regex.Matcher
import java.util.regex.Pattern


class ImageInteractorImpl : ImageInteractor {

    override fun imageObservable(weatherUrl: String): Observable<String> =
            Observable.fromCallable { getMeteogramUri(weatherUrl).toString() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    fun getMeteogramUri(weatherUrl: String): Uri? {
        val scriptFromHtmlCode = getScriptFromHtml(weatherUrl)
        val variables = getVariablesFromScript(scriptFromHtmlCode)

        var meteorogramParams: String? = null
        while (variables.find()) {
            meteorogramParams = variables.group()
        }

        if (meteorogramParams != null) {
            //example result: var fcstdate = "2015062612";var ntype ="0n";var lang ="pl";var id="462";var act_x = 232;var act_y = 466;
            meteorogramParams = formatMeteogramParams(meteorogramParams)
            val meteogramImgAddress = getMeteogramImgAddress(meteorogramParams)
            return Uri.parse(meteogramImgAddress)
        } else {
            return null
        }
    }

    private fun getScriptFromHtml(weatherUrl: String): Element {
        //todo get something in case of no script
        val document = Jsoup.connect(weatherUrl).timeout(10000).get()
        return document.select("script").last()
    }

    private fun getVariablesFromScript(scriptFromHtmlCode: Element): Matcher {
        val pattern = Pattern.compile("(?s)var\\s??(.+?);var\\s??(.+?)ntype(.+?);\\n")
        return pattern.matcher(scriptFromHtmlCode.html())
    }

    private fun formatMeteogramParams(meteorogramParams: String): String {
        return meteorogramParams.replace("\"", "")
                .replace(";var ", "&")
                .replace("var ", "?")
                .replace("fcstdate", "fdate")
                .replace("act_x", "col")
                .replace("act_y", "row")
                .replace(" ", "")
                .replace(";", "")
    }

    private fun getMeteogramImgAddress(meteorogramParams: String): String {
        val meteorogramImg = StringBuilder("http://www.meteo.pl")
        meteorogramImg.append("/um/metco/mgram_pict.php")
        meteorogramImg.append(meteorogramParams)
        return meteorogramImg.toString()
    }
}
