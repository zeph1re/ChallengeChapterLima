package binar.ganda.challengechapterlima

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import binar.ganda.challengechapterlima.Adapter.FilmAdapter
import binar.ganda.challengechapterlima.FilmActivity.DetailList
import binar.ganda.challengechapterlima.Model.ResponseDataFilmItem
import binar.ganda.challengechapterlima.UserActivity.Profile
import binar.ganda.challengechapterlima.ViewModel.FilmViewModel
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {

    lateinit var shared : SharedPreferences

    lateinit var adapterFilm : FilmAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        getUsername()
        toProfile()
        initrecycler()
        getDataFilm()
    }

    fun getUsername() {
        shared = this.getSharedPreferences("LOGIN", Context.MODE_PRIVATE)
        val name = shared.getString("username", null)
        tv_username.text = "Welcome, $name"
    }

    fun toProfile() {
        image_profile.setOnClickListener() {
            startActivity(Intent(this, Profile::class.java))
        }
    }

    fun initrecycler() {
            rv_films.layoutManager = LinearLayoutManager(this)
            adapterFilm = FilmAdapter(){
                val clickedDataFilm = bundleOf("DATAFILM" to it)
                startActivity(Intent(this, DetailList::class.java))
            }
            rv_films.adapter = adapterFilm
        }


    fun getDataFilm() {
        val viewModel = ViewModelProvider(this).get(FilmViewModel::class.java)

        viewModel.getLivedataFilm().observe(this, Observer {
            if (it != null) {
                adapterFilm.setDataFilm(it)
                adapterFilm.notifyDataSetChanged()
                Log.d("test", it.toString())
            }
        })
        viewModel.callApiFilm()
    }

}
