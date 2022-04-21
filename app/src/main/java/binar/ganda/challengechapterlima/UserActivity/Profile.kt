package binar.ganda.challengechapterlima.UserActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import binar.ganda.challengechapterlima.Adapter.FilmAdapter
import binar.ganda.challengechapterlima.R
import binar.ganda.challengechapterlima.ViewModel.FilmViewModel

class Profile : AppCompatActivity() {

//    lateinit var adapterUser : UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


    }

//    fun getDataUser() {
//        val viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
//
//        viewModel.getLivedataFilm().observe(this, Observer {
//            if (it != null) {
//                adapterFilm.setDataFilm(it)
//                adapterFilm.notifyDataSetChanged()
//                Log.d("test", it.toString())
//            }
//        })
//        viewModel.callApiFilm()
//    }
}