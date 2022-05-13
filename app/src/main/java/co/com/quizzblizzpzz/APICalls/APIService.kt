package co.com.quizzblizzpzz.APICalls

import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getQuestion(@Url url:String):retrofit2.Response<ResponseQuestion>
}