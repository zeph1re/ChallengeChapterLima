package binar.ganda.challengechapterlima.Fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import binar.ganda.challengechapterlima.Adapter.FilmAdapter
import binar.ganda.challengechapterlima.R
import binar.ganda.challengechapterlima.ViewModel.FilmViewModel
import kotlinx.android.synthetic.main.activity_home.*


class Home : Fragment() {

    private lateinit var shared : SharedPreferences
    private lateinit var adapterFilm : FilmAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        getUsername()
        toProfile()
        initrecycler()
        getDataFilm()
    }

    @SuppressLint("SetTextI18n")
    fun getUsername() {
        shared = requireActivity().getSharedPreferences("USER", Context.MODE_PRIVATE)

        val completeName = shared.getString("DATA_COMPLETE_NAME", null)

        if (completeName != null) {
            val name = completeName.split(" ")
            val fname = name[0]
            tv_username.text = "Hello, $fname"
        } else {
            tv_username.text = "Hello, there!"
        }

    }

    private fun toProfile() {
        image_profile.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_home2_to_profile)
        }
    }

    private fun initrecycler() {
        rv_films.layoutManager = LinearLayoutManager(requireContext())
        adapterFilm = FilmAdapter(){
            val clickedFilm = bundleOf("DATA_FILM" to it)
            Navigation
                .findNavController(requireView())
                .navigate(R.id.action_home2_to_detailFilm, clickedFilm)
        }
        rv_films.adapter = adapterFilm
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun getDataFilm() {
        val viewModel = ViewModelProvider(this).get(FilmViewModel::class.java)

        viewModel.getLivedataFilm().observe(viewLifecycleOwner) {
            if (it != null) {
                adapterFilm.setDataFilm(it)
                adapterFilm.notifyDataSetChanged()
                Log.d("test", it.toString())
            }
        }
        viewModel.callApiFilm()
    }
}