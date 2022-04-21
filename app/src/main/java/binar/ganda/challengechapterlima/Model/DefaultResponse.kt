package binar.ganda.challengechapterlima.Model

import com.google.gson.annotations.SerializedName

data class DefaultResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String
)
