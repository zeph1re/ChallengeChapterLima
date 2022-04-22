package binar.ganda.challengechapterlima.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import binar.ganda.challengechapterlima.Model.DefaultResponse
import binar.ganda.challengechapterlima.Network.RetrofitClient
import binar.ganda.challengechapterlima.R
import kotlinx.android.synthetic.main.fragment_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Register : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Action Of Register Button
        register_btn.setOnClickListener {
            val username = input_username.text.toString()
            val password = input_password.text.toString()
            val email = input_email.text.toString()
            val konfirmasiPassword = input_confirm_password.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty() &&
                konfirmasiPassword.isNotEmpty()
            ) {
                //check similarity of password and konfirmasiPassword
                if (password == konfirmasiPassword) {
                    postDataUser(email, username, password)
                    Toast.makeText(requireContext(), "Berhasil Registrasi", Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(requireContext(), "Password dan konfirmasi password harus sama", Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(requireContext(), "Semua field harus diisi", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun postDataUser(
        email : String,
        username : String,
        password : String
    ) {
        RetrofitClient.INSTANCE.registerUser(email, username, password)
            .enqueue(object : Callback<DefaultResponse> {
                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    if(response.isSuccessful){
                        Toast.makeText(requireContext(), response.message(), Toast.LENGTH_LONG).show()
                        Navigation.findNavController(view!!).navigate(R.id.action_register_to_login)
                    } else {
                        Toast.makeText(requireContext(), response.message(), Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()
                }


            })
    }
}