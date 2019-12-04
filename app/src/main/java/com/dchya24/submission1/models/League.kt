package com.dchya24.submission1.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    val id: String?,
    val name: String?,
    val description: String?,
    val logo: Int?) : Parcelable