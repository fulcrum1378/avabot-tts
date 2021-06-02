package ir.avabot.tts

import android.media.MediaPlayer
import android.speech.tts.*
import android.widget.Toast
import java.util.*

// adb connect 192.168.1.4:

class AvaBotService : TextToSpeechService() {
    companion object {
        val FARSI = arrayOf("fas", "pes", "")
    }

    override fun onIsLanguageAvailable(lang: String?, country: String?, variant: String?): Int =
        when (lang) {
            FARSI[0] -> TextToSpeech.LANG_AVAILABLE
            else -> TextToSpeech.LANG_NOT_SUPPORTED
        }

    override fun onLoadLanguage(lang: String?, country: String?, variant: String?): Int =
        when (lang) {
            FARSI[0] -> TextToSpeech.LANG_AVAILABLE
            else -> TextToSpeech.LANG_NOT_SUPPORTED
        }

    override fun onGetVoices(): MutableList<Voice> = mutableListOf(
        Voice(
            "Persian", Locale("fa_IR"),
            Voice.LATENCY_VERY_LOW, Voice.QUALITY_NORMAL,
            false, null
        )
    )

    override fun onGetLanguage(): Array<String> = FARSI

    override fun onStop() {
    }

    override fun onSynthesizeText(request: SynthesisRequest?, callback: SynthesisCallback?) {
        Toast.makeText(applicationContext, request?.charSequenceText?.toString(), Toast.LENGTH_LONG)
            .show()
    }
}
