package edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
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

        var playerMove: Int = 0

        // TODO refatorar metodo - escolhaUsuario
        activityMainBinding.paperIv.setOnClickListener{
            playerMove = 1
        }
        activityMainBinding.rockIv.setOnClickListener{
            playerMove = 2
        }
        activityMainBinding.scissorsIv.setOnClickListener{
            playerMove = 3
        }
        //

        randomicGenerator = Random(System.currentTimeMillis())

        activityMainBinding.playBt.setOnClickListener {

            activityMainBinding.resultTv.visibility = View.INVISIBLE

            // TODO refatorar metodo - imagemPC
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

            // TODO refatorar
            if(playerMove ==  cpuMove){
                activityMainBinding.resultTv.setText("Empatou")

            } else {
                if(playerMove == 1 ) {
                    if(cpuMove == 2){
                        activityMainBinding.resultTv.setText("Você ganhou")
                    } else {
                        activityMainBinding.resultTv.setText("Você perdeu")
                    }
                } else if(playerMove == 2){
                    if(cpuMove == 3){
                        activityMainBinding.resultTv.setText("Você ganhou")
                    } else {
                        activityMainBinding.resultTv.setText("Você perdeu")
                    }
                } else {
                    if(cpuMove == 1){
                        activityMainBinding.resultTv.setText("Você ganhou")
                    } else {
                        activityMainBinding.resultTv.setText("Você perdeu")
                    }
                }
            }

            activityMainBinding.resultTv.visibility = View.VISIBLE
        }





        settingsActivityLaucher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
                if(result.resultCode == RESULT_OK){
                    //TODO modificacoes na view
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