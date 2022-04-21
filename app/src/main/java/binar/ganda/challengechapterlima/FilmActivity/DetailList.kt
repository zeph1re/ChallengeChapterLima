package binar.ganda.challengechapterlima.FilmActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import binar.ganda.challengechapterlima.Model.ResponseDataFilmItem
import binar.ganda.challengechapterlima.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_list.*

class DetailList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_list)


        val detailFilm = intent.getParcelableExtra<ResponseDataFilmItem>("detail_film")

        tv_detail_judul.text = detailFilm?.title
        tv_detail_tanggal_rilis.text = detailFilm?.releaseDate
        tv_detail_sutradara.text = detailFilm?.director
        tv_detail_sinopsis.text = detailFilm?.synopsis

        Glide.with(this).load(detailFilm?.image).into(detail_image)

    }



}