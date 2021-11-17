package ir.avabot.tts

import android.content.Context
import android.content.SharedPreferences
import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.speech.tts.*
import android.util.Log
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONObject
import java.util.*

// adb connect 192.168.1.20:

class AvaBotService : TextToSpeechService() {
    private lateinit var pool: SoundPool
    private lateinit var sp: SharedPreferences
    private lateinit var c: Context
    private val farsi = arrayOf("fas", "pes", "")
    private var config: JSONObject? = null

    companion object {
        const val dfPath = "https://mahdiparastesh.ir/avabot/"
        const val configUrl = dfPath + "config.py"
        const val spPath = "path"
        const val spVoices = "voices"
        val dfVoices = setOf("fa_te_maryam")
        const val exFormat = "format"
        const val dfFormat = "m4a"
    }

    override fun onCreate() {
        super.onCreate()
        c = applicationContext
        //sp = c.getSharedPreferences("config", Context.MODE_PRIVATE)
        /*Volley.newRequestQueue(c).add(
            JsonObjectRequest(Request.Method.GET, configUrl, null, { res ->
                config = res
                sp.edit().apply {
                    putString(spPath, config!![spPath] as String)
                    @Suppress("UNCHECKED_CAST")
                    putStringSet(spVoices, config!![spVoices] as Set<String>)
                    ///////////////////////////////////////
                    putString(exFormat, config!![exFormat] as String)
                    apply()
                }
            }, {
                config = JSONObject()
                config!!.put(spPath, sp.getString(spPath, dfPath))
                config!!.put(spVoices, sp.getStringSet(spVoices, dfVoices))
                config!!.put(exFormat, sp.getString(exFormat, dfFormat))
            }).setShouldCache(false).setTag("talk").setRetryPolicy(
                DefaultRetryPolicy(
                    10000, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
            )
        )*/
        pool = SoundPool.Builder().apply {
            setMaxStreams(2)  // simultaneous
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build()
            )
        }.build()
    }


    override fun onIsLanguageAvailable(lang: String?, country: String?, variant: String?): Int =
        when (lang) {
            farsi[0] -> TextToSpeech.LANG_AVAILABLE
            else -> TextToSpeech.LANG_NOT_SUPPORTED
        }

    override fun onLoadLanguage(lang: String?, country: String?, variant: String?): Int =
        when (lang) {
            farsi[0] -> TextToSpeech.LANG_AVAILABLE
            else -> TextToSpeech.LANG_NOT_SUPPORTED
        }

    override fun onGetVoices(): MutableList<Voice> = mutableListOf(
        Voice(
            "Maryam", Locale("fa_IR"),
            Voice.LATENCY_VERY_LOW, Voice.QUALITY_NORMAL,
            false, null
        )
    )

    override fun onGetLanguage(): Array<String> = farsi

    override fun onStop() {
    }

    override fun onSynthesizeText(request: SynthesisRequest?, callback: SynthesisCallback?) {
        if (request == null || request.charSequenceText == null) return
        val sentences = Analyzer(request.charSequenceText.toString())
        Toast.makeText(
            c, Gson().toJson(sentences, Analyzer::class.java), Toast.LENGTH_LONG
        ).show()
    }
}
