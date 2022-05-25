package edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura.databinding.ActivityMainBinding
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var randomicGenerator: Random

    private lateinit var settingsActivityLaucher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        randomicGenerator = Random(System.currentTimeMillis())

        activityMainBinding.playBt.setOnClickListener {
            val cpuMove: Int = randomicGenerator.nextInt(1..3)
            if(cpuMove == 1){
                activityMainBinding.cpuIv.setImageResource(
                    resources.getIdentifier("paper", "drawable", packageName)
                )
            } else if(cpuMove == 2){
                activityMainBinding.cpuIv.setImageResource(
                    resources.getIdentifier("rock", "drawable", packageName)
                )
            } else {
                activityMainBinding.cpuIv.setImageResource(
                    resources.getIdentifier("scissors", "drawable", packageName)
                )
            }
        }

        val playerMovePaper = activityMainBinding.paperIv.setOnClickListener{
            Toast.makeText(this, "Clicou em papel", Toast.LENGTH_SHORT).show()
        }



        settingsActivityLaucher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
                if(result.resultCode == RESULT_OK){
                    // modificacoes na view
                }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.settingsMi){
            val settingsIntent = Intent(this, GameSettingsActivity::class.java)
            settingsActivityLaucher.launch(settingsIntent)
            return true
        }
        return false
    }
}