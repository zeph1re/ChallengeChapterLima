package binar.ganda.challengechapterlima.Network

import binar.ganda.challengechapterlima.Model.DefaultResponse
import binar.ganda.challengechapterlima.Model.LoginResponse
import binar.ganda.challengechapterlima.Model.ResponseDataFilmItem
import binar.ganda.challengechapterlima.Model.ResponseDataUserItem
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET ("apifilm.php")
    fun getAllFilm() : Call<List<ResponseDataFilmItem>>

    @POST ("login.php")
    @FormUrlEncoded
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password : String
    ) : Call<LoginResponse>

    @POST ("register.php")
    @FormUrlEncoded
    fun registerUser(
        @Field ("email") email: String,
        @Field ("username") username: String,
        @Field ("password") password: String
    ) : Call<DefaultResponse>

    @POST ("updateuser.php/{id}")
    @FormUrlEncoded
    fun updateUser(
        @Field ("id") id: Int,
        @Field ("username") username: String,
        @Field ("complete_name") complete_name: String,
        @Field ("address") address: String,
        @Field ("dateOfBirth") dateOfBirth: String,
    )


    @GET ("apiuser.php")
    fun getAllUser() : Call<List<ResponseDataUserItem>>
}