package edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura.dao

import edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura.model.AppSettings

interface AppSettingsDAO {

    fun create(appSetting: AppSettings): Long
    fun update(appSetting: AppSettings): Int
    fun recover(): AppSettings
}