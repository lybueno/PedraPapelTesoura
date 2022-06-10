package edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura

import android.content.Intent
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
import edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura.controller.AppSettingsController
import edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura.databinding.ActivityMainBinding
import edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura.model.AppSettings
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var randomicGenerator: Random

    private lateinit var settingsActivityLaucher: ActivityResultLauncher<Intent>

    private val appSettingsController: AppSettingsController by lazy {
        AppSettingsController(this)
    }

    private val appSettings: AppSettings by lazy {
        appSettingsController.recoverSetting()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        var playerMove: Int = 0
        val paper: ImageView = activityMainBinding.paperIv
        val rock: ImageView = activityMainBinding.rockIv
        val scissors: ImageView =  activityMainBinding.scissorsIv

        // Tratando a imagem escolhida pelo usuario e atribuindo um valor a ela
        paper.setOnClickListener{
            playerMove = 1
            changeBackgroundColor(paper, rock, scissors)
        }
        rock.setOnClickListener{
            playerMove = 2
            changeBackgroundColor(rock, paper, scissors)
        }
       scissors.setOnClickListener{
            playerMove = 3
            changeBackgroundColor(scissors, paper, rock)
        }

        // TODO refatorar jogo de 2 oponentes para funcao e chamar com o botao e a GameSettingsActivity
        if(appSettings.playersNumber == 2){
            activityMainBinding.cpuIv.setImageResource(
                resources.getIdentifier("cpu", "drawable", packageName)
            )
            activityMainBinding.cpu2Iv.visibility = View.VISIBLE
        } else {
            activityMainBinding.cpu2Iv.visibility = View.GONE
        }

        randomicGenerator = Random(System.currentTimeMillis())

        activityMainBinding.playBt.setOnClickListener {

            activityMainBinding.resultTv.visibility = View.INVISIBLE

            // comparando as jogadas e atribuindo o placar
            if(playerMove == 0) {
                Toast.makeText(this, "Selecione uma opção", Toast.LENGTH_SHORT).show()
            }else{

                val cpuMove: Int = generateCPUMove(activityMainBinding.cpuIv)
                val result: String

                if(appSettings.playersNumber == 1){
                    if(playerMove ==  cpuMove){
                        result = "Empatou"
                    } else {
                        if(playerMove == 1 ) {
                            if(cpuMove == 2){
                                result = "Você ganhou"
                            } else {
                                result = "Você perdeu"
                            }
                        } else if(playerMove == 2){
                            if(cpuMove == 3){
                                result = "Você ganhou"
                            } else {
                                result = "Você perdeu"
                            }
                        } else {
                            if(cpuMove == 1){
                                result = "Você ganhou"
                            } else {
                                result = "Você perdeu"
                            }
                        }
                    }
                } else {
                    val cpuMove: Int = generateCPUMove(activityMainBinding.cpuIv)
                    val cpu2Move: Int = generateCPUMove(activityMainBinding.cpu2Iv)
                    val result: String

                    if (playerMove == 1) {
                        if (cpuMove == 3 || cpu2Move == 3) {
                            result = "Você perdeu"
                        } else if (cpuMove == 1 || cpu2Move == 1) {
                            result = "Empatou"
                        } else {
                            result = "Você ganhou"
                        }
                    } else if (playerMove == 2) {
                        if (cpuMove == 1 || cpu2Move == 1) {
                            result = "Você perdeu"
                        } else if (cpuMove == 2 || cpu2Move == 2) {
                            result = "Empatou"
                        } else {
                            result = "Você ganhou"
                        }
                    } else {
                        if (cpuMove == 2 || cpu2Move == 2) {
                            result = "Você perdeu"
                        } else if (cpuMove == 3 || cpu2Move == 3) {
                            result = "Empatou"
                        } else {
                            result = "Você ganhou"
                        }
                    }
                    activityMainBinding.resultTv.setText(result)
                }

            }

            activityMainBinding.resultTv.visibility = View.VISIBLE
        }

        // verificando se usuario selecionou 2 oponentes para tratar o placar e as imagens dos oponentes
        settingsActivityLaucher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
                if(result.resultCode == RESULT_OK) {

                    activityMainBinding.resultTv.visibility = View.INVISIBLE

                    if (result.data != null) {
                        val appSettingsRl: AppSettings? =
                            result.data?.getParcelableExtra<AppSettings>(Intent.EXTRA_USER)

                        if (appSettingsRl != null) {

                            if(appSettings.isCreated){
                                appSettingsController.insertsetting(appSettingsRl)
                            } else {
                                appSettingsController.updateSettings(appSettingsRl)
                            }

                            if (appSettingsRl.playersNumber == 2) {
                                activityMainBinding.cpuIv.setImageResource(
                                    resources.getIdentifier("cpu", "drawable", packageName)
                                )
                                activityMainBinding.cpu2Iv.visibility = View.VISIBLE
                                activityMainBinding.playBt.setOnClickListener {

                                    if(playerMove == 0) {
                                        Toast.makeText(this, "Selecione uma opção", Toast.LENGTH_SHORT).show()
                                    }else {
                                        val cpuMove: Int = generateCPUMove(activityMainBinding.cpuIv)
                                        val cpu2Move: Int = generateCPUMove(activityMainBinding.cpu2Iv)
                                        val result: String

                                        if (playerMove == 1) {
                                            if (cpuMove == 3 || cpu2Move == 3) {
                                                result = "Você perdeu"
                                            } else if (cpuMove == 1 || cpu2Move == 1) {
                                                result = "Empatou"
                                            } else {
                                                result = "Você ganhou"
                                            }
                                        } else if (playerMove == 2) {
                                            if (cpuMove == 1 || cpu2Move == 1) {
                                                result = "Você perdeu"
                                            } else if (cpuMove == 2 || cpu2Move == 2) {
                                                result = "Empatou"
                                            } else {
                                                result = "Você ganhou"
                                            }
                                        } else {
                                            if (cpuMove == 2 || cpu2Move == 2) {
                                                result = "Você perdeu"
                                            } else if (cpuMove == 3 || cpu2Move == 3) {
                                                result = "Empatou"
                                            } else {
                                                result = "Você ganhou"
                                            }
                                        }

                                        activityMainBinding.resultTv.setText(result)
                                        activityMainBinding.resultTv.visibility = View.VISIBLE
                                    }
                                }
                            } else {
                                activityMainBinding.cpu2Iv.visibility = View.GONE
                                activityMainBinding.cpuIv.setImageResource(
                                    resources.getIdentifier("cpu", "drawable", packageName)
                                )
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

    private fun changeBackgroundColor(yellowIv: ImageView, whiteIv: ImageView, white2Iv: ImageView){
        yellowIv.setBackgroundColor(Color.parseColor("#FFF000"))
        whiteIv.setBackgroundColor(Color.parseColor("#FFFFFF"))
        white2Iv.setBackgroundColor(Color.parseColor("#FFFFFF"))
    }
}