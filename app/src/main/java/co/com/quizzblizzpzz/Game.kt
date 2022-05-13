package co.com.quizzblizzpzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.com.quizzblizzpzz.APICalls.APIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Game : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        searchByAmount(1)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(resources.getString(R.string.BASE_URL))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByAmount(amount: Int ){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit()
                .create(APIService::class.java)
                .getQuestion(resources.getString(R.string.PHP_BASE_URL)+"amount=$amount")
            val res = call.body()
            if (call.isSuccessful){
                System.out.println("*** TAG ${res.toString()}")
            }else{
                System.out.println("*** TAG ERROR")
            }
        }
    }
}