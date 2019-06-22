package tat.flexsentlabs.webviewapp

import retrofit2.Call
import retrofit2.http.GET


interface Api {

    @GET("users/mojombo")
    fun getLink(): Call<User>
}