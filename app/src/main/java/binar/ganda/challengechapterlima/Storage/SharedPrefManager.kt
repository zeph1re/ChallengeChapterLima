package binar.ganda.challengechapterlima.Storage

import android.content.Context
import binar.ganda.challengechapterlima.Model.ResponseUser


class SharedPrefManager private constructor(private val mCtx: Context) {

    val isLoggedIn: Boolean
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getInt("id", -1) != -1
        }

    val user: ResponseUser
        get() {
            val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return ResponseUser(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("email", null).toString(),
                sharedPreferences.getString("username", null).toString(),
                sharedPreferences.getString("password", null).toString(),
                sharedPreferences.getString("complete_name", null).toString(),
                sharedPreferences.getString("address", null).toString(),
                sharedPreferences.getString("dateofbirth", null).toString(),
            )
        }


    fun saveUser(user: ResponseUser) {

        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putInt("id", user.id)
        editor.putString("email", user.email)
        editor.putString("username", user.username)
        editor.putString("password", user.password)

        editor.apply()

    }

    fun clear() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private val SHARED_PREF_NAME = "my_shared_preff"
        private var mInstance: SharedPrefManager? = null
        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance as SharedPrefManager
        }
    }

}