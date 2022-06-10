package edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura.databinding.ActivityGameSettingsBinding
import edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura.model.AppSettings

class GameSettingsActivity : AppCompatActivity() {

    private lateinit var activityGameSettingsBinding: ActivityGameSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityGameSettingsBinding = ActivityGameSettingsBinding.inflate(layoutInflater)
        setContentView(activityGameSettingsBinding.root)

        activityGameSettingsBinding.SaveBt.setOnClickListener {
            val playersNumber: Int = (activityGameSettingsBinding.playersNumberSp.selectedView as TextView).text.toString().toInt()
            val appSettings = AppSettings(playersNumber)
            val returnIntent = Intent()
            returnIntent.putExtra(Intent.EXTRA_USER, appSettings)
            setResult(RESULT_OK, returnIntent)
            finish()
        }
    }
}