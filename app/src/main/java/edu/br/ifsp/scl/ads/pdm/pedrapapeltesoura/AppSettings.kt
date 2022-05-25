package edu.br.ifsp.scl.ads.pdm.pedrapapeltesoura

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AppSettings(val playersNumber: Int = 1): Parcelable
