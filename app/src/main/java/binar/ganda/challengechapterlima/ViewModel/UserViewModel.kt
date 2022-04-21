package binar.ganda.challengechapterlima.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import binar.ganda.challengechapterlima.Model.ResponseDataUserItem
import binar.ganda.challengechapterlima.Network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    var liveDataUser : MutableLiveData<List<ResponseDataUserItem>> = MutableLiveData()

    fun getLivedataUser() : MutableLiveData<List<ResponseDataUserItem>>{
        return liveDataUser
    }

    fun callApiUser() {
        with(RetrofitClient){
            INSTANCE.getAllUser()
                .enqueue(object : Callback<List<ResponseDataUserItem>>{
                    override fun onResponse(
                        call: Call<List<ResponseDataUserItem>>,
                        response: Response<List<ResponseDataUserItem>>
                    ){
                        if (response.isSuccessful){
                            liveDataUser.postValue(response.body())
                        } else {
                            liveDataUser.postValue(null)
                        }
                    }

                    override fun onFailure(call: Call<List<ResponseDataUserItem>>, t: Throwable) {
                        liveDataUser.postValue(null)
                    }

                })
        }
    }

}