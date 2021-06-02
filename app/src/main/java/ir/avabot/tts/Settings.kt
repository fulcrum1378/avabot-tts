package ir.avabot.tts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.avabot.tts.databinding.SettingsBinding

class Settings : AppCompatActivity() {
    private lateinit var b: SettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = SettingsBinding.inflate(layoutInflater)
        setContentView(b.root)
    }
}