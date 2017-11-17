package biz.fastgroup.apps.paloup.rest

import com.google.gson.JsonObject

import biz.fastgroup.apps.paloup.models.LoginCredentials
import biz.fastgroup.apps.paloup.models.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.PUT
import rx.Observable

/**
 * Created by gronel on 25/03/2017.
 */

interface ApiInterface {

    @GET("access/getuser")
    @Headers("No-Authentication: true")
    fun getuser(@Query("loginname") loginname: String, @Query("password") password: String): Call<UserModel>

    @POST("access/LoginUser")
    @Headers("No-Authentication: true")
    fun LoginUser(@Body data: LoginCredentials): Observable<JsonObject>

}
