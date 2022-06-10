package edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AppSettings(val playersNumber: Int = 1, val isCreated: Boolean = false): Parcelable
