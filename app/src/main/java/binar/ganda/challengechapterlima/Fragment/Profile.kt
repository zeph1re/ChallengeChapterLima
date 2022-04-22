package binar.ganda.challengechapterlima.Fragment

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import binar.ganda.challengechapterlima.Model.DefaultResponse
import binar.ganda.challengechapterlima.Network.RetrofitClient
import binar.ganda.challengechapterlima.R
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Response


class Profile : Fragment() {

    private lateinit var shared : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDataUser()

        update_btn.setOnClickListener {
            shared = requireActivity().getSharedPreferences("USER", Context.MODE_PRIVATE)
            val usernameNew = edit_username.text.toString()
            val namaLengkapNew = edit_nama_lengkap.text.toString()
            val tanggalLahirNew = edit_tanggal_lahir.text.toString()
            val alamatNew = edit_alamat.text.toString()
            val id = shared.getString("DATA_ID", null)
            updateUserData(id!!, usernameNew, namaLengkapNew, tanggalLahirNew ,alamatNew)
        }


        logout_btn.setOnClickListener{
            logout()
        }
    }

    private fun setDataUser() {
        shared = requireActivity().getSharedPreferences("USER", Context.MODE_PRIVATE)
        val username = shared.getString("DATA_USERNAME", null)
        val namaLengkap = shared.getString("DATA_COMPLETE_NAME", null)
        val tanggalLahir = shared.getString("DATA_DATE_OF_BIRTH", null)
        val alamat = shared.getString("DATA_ADDRESS", null)

        edit_username.setText(username)
        edit_nama_lengkap.setText(namaLengkap)
        edit_tanggal_lahir.setText(tanggalLahir)
        edit_alamat.setText(alamat)
    }

    private fun updateUserData(
        id : String,
        username : String,
        completeName : String,
        dateofbirth : String,
        address : String) {
        RetrofitClient.INSTANCE.updateUser(id ,username, completeName,dateofbirth,address)
            .enqueue(object : retrofit2.Callback<DefaultResponse> {
                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(requireContext(), response.message(), Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(requireContext(), response.message(), Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()
                }

            })

    }

    private fun logout(){
        AlertDialog.Builder(requireContext())
            .setTitle("LOGOUT")
            .setMessage("Yakin ingin logout?")
            .setNegativeButton("Tidak"){ dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }.setPositiveButton("Ya"){ _: DialogInterface, _: Int ->
                //clear shared preference, so the user must login again to access home after logging out
                val sharedPreferences = requireContext().getSharedPreferences("USER", Context.MODE_PRIVATE)
                val sf = sharedPreferences.edit()
                sf.clear()
                sf.apply()

                //reload activity
                val mIntent = activity?.intent
                activity?.finish()
                startActivity(mIntent)
            }.show()
    }
}