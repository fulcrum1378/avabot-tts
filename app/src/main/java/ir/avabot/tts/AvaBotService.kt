package ir.avabot.tts

import android.media.AudioAttributes
import android.media.SoundPool
import android.speech.tts.*
import java.util.*

// adb connect 192.168.1.4:

@Suppress("MemberVisibilityCanBePrivate")
class AvaBotService : TextToSpeechService() {
    lateinit var pool: SoundPool
    val c = applicationContext
    val farsi = arrayOf("fas", "pes", "")

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
        val words = Analyzer(this, request?.charSequenceText)
        if (words.size == 0) return

    }


    override fun onCreate() {
        super.onCreate()
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
}
