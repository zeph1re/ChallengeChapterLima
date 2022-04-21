package binar.ganda.challengechapterlima.UserActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import binar.ganda.challengechapterlima.Model.DefaultResponse
import binar.ganda.challengechapterlima.Network.RetrofitClient
import binar.ganda.challengechapterlima.R
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_btn.setOnClickListener() {
            val username = input_username.text.toString()
            val email = input_email.text.toString()
            val password = input_password.text.toString()
            val confirmPassword = input_confirm_password.text.toString()

            if  (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
                if (password == confirmPassword){
                    postDataUser(username,email,password)
                } else {
                    Toast.makeText(this, "Password dan Confirm Password harus sama", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun postDataUser(
        username : String,
        email : String,
        password : String
    ) {
        RetrofitClient.INSTANCE.registerUser(username, email, password)
            .enqueue(object : Callback<DefaultResponse> {
                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    if(response.isSuccessful){
                        Toast.makeText(this@Register, response.message(), Toast.LENGTH_LONG).show()
                        startActivity(Intent(this@Register, Login::class.java))
                    } else {
                        Toast.makeText(this@Register, response.message(), Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Toast.makeText(this@Register, t.message, Toast.LENGTH_LONG).show()
                }


            })
    }
}