package ir.avabot.tts

import android.speech.tts.SynthesisCallback
import android.speech.tts.SynthesisRequest
import android.speech.tts.TextToSpeechService

// adb connect 192.168.1.4:

class AvaBotService : TextToSpeechService() {
    override fun onIsLanguageAvailable(lang: String?, country: String?, variant: String?): Int {
        TODO("Not yet implemented")
    }

    override fun onGetLanguage(): Array<String> {
        TODO("Not yet implemented")
    }

    override fun onLoadLanguage(lang: String?, country: String?, variant: String?): Int {
        TODO("Not yet implemented")
    }

    override fun onStop() {
        TODO("Not yet implemented")
    }

    override fun onSynthesizeText(request: SynthesisRequest?, callback: SynthesisCallback?) {
        TODO("Not yet implemented")
    }
}
