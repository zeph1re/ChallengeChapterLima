package binar.ganda.challengechapterlima.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import binar.ganda.challengechapterlima.Model.ResponseDataFilmItem
import binar.ganda.challengechapterlima.Network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FilmViewModel : ViewModel() {

    lateinit var liveDataFilm : MutableLiveData<List<ResponseDataFilmItem>>

    init {
        liveDataFilm = MutableLiveData()
    }

    fun getLivedataFilm() : MutableLiveData<List<ResponseDataFilmItem>> {
        return liveDataFilm
    }

    fun callApiFilm() {
        with(RetrofitClient) {
            INSTANCE.getAllFilm()
                .enqueue(object : Callback<List<ResponseDataFilmItem>>{
                    override fun onResponse(
                        call: Call<List<ResponseDataFilmItem>>,
                        response: Response<List<ResponseDataFilmItem>>
                    ) {
                        if (response.isSuccessful){
                            liveDataFilm.postValue(response.body())
                        } else{
                            liveDataFilm.postValue(null)
                        }
                    }

                    override fun onFailure(call: Call<List<ResponseDataFilmItem>>, t: Throwable) {
                        liveDataFilm.postValue(null)
                    }

                })
        }
    }
}
