package ir.avabot.tts

import android.media.MediaPlayer
import android.speech.tts.SynthesisCallback
import android.speech.tts.SynthesisRequest
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeechService
import android.widget.Toast

// adb connect 192.168.1.4:

class AvaBotService : TextToSpeechService() {
    companion object {
        const val PERSIAN = "fas"
    }

    override fun onIsLanguageAvailable(lang: String?, country: String?, variant: String?): Int =
        when (lang) {
            PERSIAN -> TextToSpeech.LANG_AVAILABLE
            else -> TextToSpeech.LANG_NOT_SUPPORTED
        }

    override fun onGetLanguage(): Array<String> = arrayOf("fas", "pes", "")

    override fun onLoadLanguage(lang: String?, country: String?, variant: String?): Int =
        when (lang) {
            PERSIAN -> TextToSpeech.LANG_AVAILABLE
            else -> TextToSpeech.LANG_NOT_SUPPORTED
        }

    override fun onStop() {
    }

    override fun onSynthesizeText(request: SynthesisRequest?, callback: SynthesisCallback?) {
        Toast.makeText(applicationContext, request?.charSequenceText?.toString(), Toast.LENGTH_LONG).show()
    }
}
