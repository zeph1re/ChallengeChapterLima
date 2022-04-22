package binar.ganda.challengechapterlima.Fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import binar.ganda.challengechapterlima.Model.ResponseDataUserItem
import binar.ganda.challengechapterlima.R
import binar.ganda.challengechapterlima.ViewModel.UserViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class Login : Fragment() {

    private lateinit var viewModel : UserViewModel
    private lateinit var listUser : List<ResponseDataUserItem>
    private lateinit var sharedPreference : SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        belum_punya_akun_btn.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_login_to_register)
        }

        login_btn.setOnClickListener{
            if (input_email.text.isNotEmpty() && input_password.text.isNotEmpty()){

                viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
                viewModel.getLivedataUser().observe(viewLifecycleOwner, Observer {
                    listUser = it

                    sharedPreference = requireActivity().getSharedPreferences("USER", Context.MODE_PRIVATE)
                    //checking email and password of user to authenticate
                    for(i in listUser.indices){
                        val emailLogin = input_email.text.toString()
                        val passwordLogin = input_password.text.toString()
                        if(passwordLogin == listUser[i].password && emailLogin == listUser[i].email){
                            Toast.makeText(requireContext(), "Berhasil login", Toast.LENGTH_SHORT).show()
                            //bundling all information about user
                            val sharedPref = sharedPreference.edit()
                            sharedPref.putString("DATA_ID", listUser[i].id)
                            sharedPref.putString("DATA_EMAIL", listUser[i].email)
                            sharedPref.putString("DATA_PASSWORD", listUser[i].password)
                            sharedPref.putString("DATA_USERNAME", listUser[i].username)
                            sharedPref.putString("DATA_COMPLETE_NAME", listUser[i].completeName)
                            sharedPref.putString("DATA_ADDRESS", listUser[i].address)
                            sharedPref.putString("DATA_DATE_OF_BIRTH", listUser[i].dateofbirth)
                            sharedPref.apply()

                            Navigation.findNavController(view).navigate(R.id.action_login_to_home2)
                        }
                        if(i == listUser.lastIndex && emailLogin != listUser[i].password && passwordLogin != listUser[i].email){
                            Toast.makeText(requireContext(), "Gagal login", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
                viewModel.callApiUser()
            } else {
                Toast.makeText(requireContext(),"Email dan Password harus di isi", Toast.LENGTH_LONG).show()
            }
        }
    }
}