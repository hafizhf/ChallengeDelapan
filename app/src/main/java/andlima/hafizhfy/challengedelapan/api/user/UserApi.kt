package andlima.hafizhfy.challengedelapan.api.user

import andlima.hafizhfy.challengedelapan.model.user.GetUserItem
import andlima.hafizhfy.challengedelapan.model.user.RequestUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {
    // Login service
    @GET("users")
    fun getUser(@Query("email") email : String) : Call<List<GetUserItem>>

    // Register service
    @POST("users")
    fun postUser(@Body request: RequestUser) : Call<GetUserItem>

    // Update profile
//    @PUT("users/{id}")
//    fun updateUser(
//        @Path("id") id: Int,
//        @Body request: PutUser
//    ) : Call<GetUserItem>
}