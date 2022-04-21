package binar.ganda.challengechapterlima.UserActivity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import binar.ganda.challengechapterlima.Home
import binar.ganda.challengechapterlima.Model.LoginResponse
import binar.ganda.challengechapterlima.Model.ResponseDataUserItem
import binar.ganda.challengechapterlima.Model.ResponseUser
import binar.ganda.challengechapterlima.Network.RetrofitClient
import binar.ganda.challengechapterlima.R
import binar.ganda.challengechapterlima.Storage.SharedPrefManager
import binar.ganda.challengechapterlima.ViewModel.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {

    private lateinit var shared: SharedPreferences
    private lateinit var viewModelUser: UserViewModel
    private lateinit var listUser: List<ResponseDataUserItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        shared = this.getSharedPreferences("LOGIN", Context.MODE_PRIVATE)



        belum_punya_akun_btn.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }

        login_btn.setOnClickListener {
            if (input_email.text.isEmpty() && input_password.text.isEmpty()) {
                Toast.makeText(this, "Masukkan Username dan Password", Toast.LENGTH_LONG).show()
            } else if (input_email.text.toString().isEmpty()) {
                Toast.makeText(this, "Email Required", Toast.LENGTH_LONG).show()
            } else if (input_password.text.toString().isEmpty()){
                Toast.makeText(this, "Password Required", Toast.LENGTH_LONG).show()
            } else {
                val email = input_email.text.toString()
                val password = input_password.text.toString()

                RetrofitClient.INSTANCE.loginUser(email, password)
                    .enqueue(object : Callback<LoginResponse>{
                        override fun onResponse(
                            call: Call<LoginResponse>,
                            response: Response<LoginResponse>
                        ) {
                            if(response.isSuccessful){
                                Toast.makeText(this@Login,response.message(), Toast.LENGTH_LONG).show()
                                SharedPrefManager.getInstance(applicationContext).saveUser(response.body()!!.responseuser)

                                val pindah = Intent(this@Login, Home::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(pindah)

                            } else{
                                Toast.makeText(this@Login,response.message(), Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            Toast.makeText(this@Login,t.message, Toast.LENGTH_LONG).show()
                        }

                    })
            }
        }

    }

}

//    fun getDataUser() {
//        val viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
//
//        viewModel.getLivedataUser().observe(this, Observer {
//            if (it != null) {
//                listUser = it
//                loginAuth(listUser)
//            }
//        })
//        viewModel.callApiUser()
//    }
//
//
//    fun loginAuth(userData: List<ResponseDataUserItem>) {
//
//        shared = this.getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
//
//        //get data user
//        val emailLogin = input_email.text.toString()
//        val passwordLogin = input_password.text.toString()
//
//        for (i in userData.indices) {
//            if (emailLogin == userData[i].email && passwordLogin == userData[i].password){
//                Toast.makeText(this, "Berhasil login", Toast.LENGTH_SHORT).show()
//                //bundling all information about user
//
//                val sharedPref = shared.edit()
//                sharedPref.putString("DATAEMAIL", userData[i].email)
//                sharedPref.putString("DATAPASSWORD", userData[i].password)
//                sharedPref.putString("DATAUSERNAME", userData[i].username)
//                sharedPref.putString("DATATANGGALLAHIR", userData[i].dateofbirth)
//                sharedPref.putString("DATAALAMAT", userData[i].address)
//                sharedPref.putString("DATAID", userData[i].id)
//                sharedPref.apply()
//
//                startActivity(Intent(this, Home::class.java))
//
//            } else {
//                Toast.makeText(this, "Gagal login", Toast.LENGTH_SHORT).show()
//
//            }
//        }
//
//    }


