package com.biblublab.seloger.features

import android.os.Parcelable
import com.biblublab.seloger.common.EMPTY_STRING
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RealPropertyEntity(
    val id: Int,
    val url: String?,
    val price: String,
    val type: String,
    val otherInfosLabel: String,
    val bedRoomsInfosLabel: String = EMPTY_STRING,
    val professionalLabel: String = EMPTY_STRING) : Parcelable