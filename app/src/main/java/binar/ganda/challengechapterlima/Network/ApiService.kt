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

    @POST("updateuser.php")
    @FormUrlEncoded
    fun updateUser(
        @Field("id")id : String,
        @Field("username")username : String,
        @Field("complete_name")completename : String,
        @Field("dateofbirth")dateofbirth : String,
        @Field("address")address : String
    ): Call<DefaultResponse>


    @GET ("apiuser.php")
    fun getAllUser() : Call<List<ResponseDataUserItem>>
}