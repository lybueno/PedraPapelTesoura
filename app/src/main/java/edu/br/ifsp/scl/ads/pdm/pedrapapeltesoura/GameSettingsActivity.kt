package edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura.databinding.ActivityGameSettingsBinding
import edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura.databinding.ActivityMainBinding

class GameSettingsActivity : AppCompatActivity() {

    private lateinit var activityGameSettingsBinding: ActivityGameSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityGameSettingsBinding = ActivityGameSettingsBinding.inflate(layoutInflater)
        setContentView(activityGameSettingsBinding.root)
    }
}