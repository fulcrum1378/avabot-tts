package ir.avabot.tts

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import ir.avabot.tts.databinding.SettingsBinding

class Settings : AppCompatActivity() {
    private lateinit var tts: TextToSpeech
    private lateinit var b: SettingsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = SettingsBinding.inflate(layoutInflater)
        setContentView(b.root)


        tts = TextToSpeech(applicationContext, { }, "ir.avabot.tts")
        b.root.setOnClickListener {
            tts.speak("سلام. شما خوبی؟", TextToSpeech.QUEUE_ADD, null, "res")
        }
    }
}
