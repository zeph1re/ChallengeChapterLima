package binar.ganda.challengechapterlima.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import binar.ganda.challengechapterlima.Model.ResponseDataFilmItem
import binar.ganda.challengechapterlima.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail_film.*


class DetailFilm : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getAllDetail()
    }

    private fun getAllDetail() {
        val details = arguments?.getParcelable<ResponseDataFilmItem>("DATA_FILM")
        val judul = details?.title
        val releaseDate = details?.releaseDate
        val sutradara = details?.director
        val image = details?.image
        val sinopsis = details?.synopsis


        tv_detail_judul.text = judul
        tv_detail_sutradara.text = sutradara
        tv_detail_tanggal_rilis.text = releaseDate
        tv_detail_sinopsis.text = sinopsis

        Glide.with(requireContext()).load(image).into(detail_image)
    }
}