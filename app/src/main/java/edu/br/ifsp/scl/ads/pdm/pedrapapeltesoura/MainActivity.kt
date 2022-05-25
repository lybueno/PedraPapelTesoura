package edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura.databinding.ActivityMainBinding
import java.lang.Byte.decode
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
        val paper: ImageView = activityMainBinding.paperIv
        val rock: ImageView = activityMainBinding.rockIv
        val scissors: ImageView =  activityMainBinding.scissorsIv

        // TODO refatorar metodo - escolhaUsuario
        paper.setOnClickListener{
            playerMove = 1
            paper.setBackgroundColor(Color.parseColor("#FFF000"))
            rock.setBackgroundColor(Color.parseColor("#FFFFFF"))
            scissors.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
        rock.setOnClickListener{
            playerMove = 2
            rock.setBackgroundColor(Color.parseColor("#FFF000"))
            paper.setBackgroundColor(Color.parseColor("#FFFFFF"))
            scissors.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
       scissors.setOnClickListener{
            playerMove = 3
            scissors.setBackgroundColor(Color.parseColor("#FFF000"))
            paper.setBackgroundColor(Color.parseColor("#FFFFFF"))
            rock.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
        //

        randomicGenerator = Random(System.currentTimeMillis())

        activityMainBinding.playBt.setOnClickListener {

            activityMainBinding.resultTv.visibility = View.INVISIBLE

            // TODO refatorar metodo - imagemPC


            // TODO refatorar
            if(playerMove == 0) {
                Toast.makeText(this, "Selecione uma opção", Toast.LENGTH_SHORT).show()
            }else{
                val cpuMove: Int = generateCPUMove(activityMainBinding.cpuIv)
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
            }

            activityMainBinding.resultTv.visibility = View.VISIBLE
        }


        settingsActivityLaucher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
                if(result.resultCode == RESULT_OK) {
                    activityMainBinding.resultTv.visibility = View.INVISIBLE
                    if (result.data != null) {
                        val appSettings: AppSettings? =
                            result.data?.getParcelableExtra<AppSettings>(Intent.EXTRA_USER)

                        if (appSettings != null) {

                            activityMainBinding.cpuIv.setImageResource(
                                    resources.getIdentifier("cpu", "drawable", packageName))
                            activityMainBinding.cpu2Iv.visibility = View.VISIBLE
                            activityMainBinding.playBt.setOnClickListener {
                                
                                val cpuMove: Int = generateCPUMove(activityMainBinding.cpuIv)
                                val cpu2Move: Int = generateCPUMove(activityMainBinding.cpu2Iv)

                                if ((cpuMove == cpu2Move && cpuMove == playerMove)) {
                                    activityMainBinding.resultTv.setText("Empatou")
                                } else {
                                    if (playerMove == 1) {
                                        if (cpuMove == 1 || cpu2Move == 1) {
                                            activityMainBinding.resultTv.setText("Empatou")
                                        } else if(cpuMove == 3 || cpu2Move == 3){
                                            activityMainBinding.resultTv.setText("Você perdeu")
                                        } else {
                                            activityMainBinding.resultTv.setText("Você ganhou")
                                        }
                                    } else if (playerMove == 2) {
                                        if (cpuMove == 2 || cpu2Move == 2) {
                                            activityMainBinding.resultTv.setText("Empatou")
                                        } else if(cpuMove == 1 || cpu2Move == 1) {
                                            activityMainBinding.resultTv.setText("Você perdeu")
                                        } else {
                                            activityMainBinding.resultTv.setText("Você ganhou")
                                        }
                                    } else {
                                        if (cpuMove == 3 || cpu2Move == 3) {
                                            activityMainBinding.resultTv.setText("Empatou")
                                        } else if(cpuMove == 2 || cpu2Move == 2) {
                                            activityMainBinding.resultTv.setText("Você perdeu")
                                        } else {
                                            activityMainBinding.resultTv.setText("Você ganhou")
                                        }
                                    }
                                }
                                activityMainBinding.resultTv.visibility = View.VISIBLE
                            }
                        }
                    }
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

    private fun generateCPUMove(imageView: ImageView): Int{
        val cpuMove: Int = randomicGenerator.nextInt(1..3)
        if(cpuMove == 1){
            imageView.setImageResource(
                resources.getIdentifier("paper", "drawable", packageName)
            )
        } else if(cpuMove == 2){
            imageView.setImageResource(
                resources.getIdentifier("rock", "drawable", packageName)
            )
        } else {
            imageView.setImageResource(
                resources.getIdentifier("scissors", "drawable", packageName)
            )
        }
        return cpuMove
    }
}