package edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura.controller

import edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura.MainActivity
import edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura.dao.AppSettingsDAO
import edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura.dao.AppSettingsSqlite
import edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura.model.AppSettings

class AppSettingsController(mainActivity: MainActivity) {

    val appSettingsDAO: AppSettingsDAO = AppSettingsSqlite(mainActivity)

    fun insertsetting(settings: AppSettings) = appSettingsDAO.create(settings)
    fun recoverSetting(): AppSettings = appSettingsDAO.recover()
    fun updateSettings(settings: AppSettings) = appSettingsDAO.update(settings)
}