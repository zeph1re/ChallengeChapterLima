package binar.ganda.challengechapterlima.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseUser(
    val id : Int,
    val email: String,
    val password : String,
    val username : String,
    val complete_name: String,
    val address : String,
    val dateofbirth : String
    ) : Parcelable
