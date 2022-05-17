package co.com.quizzblizzpzz

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import co.com.quizzblizzpzz.APICalls.APIService
import co.com.quizzblizzpzz.APICalls.ResponseQuestion
import co.com.quizzblizzpzz.databinding.ActivityGameBinding
import co.com.quizzblizzpzz.question.CustomQuestionAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.Collections.shuffle
import kotlin.collections.ArrayList

class Game : AppCompatActivity(){

    private lateinit var binding: ActivityGameBinding

    var points: Int = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.listQ.layoutManager = LinearLayoutManager(this)
        points = Utils().getData(this, getString(R.string.points),"0").toInt()
        binding.txtPoints.text = points.toString()
        searchByAmount(1)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(resources.getString(R.string.BASE_URL))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun searchByAmount(amount: Int ){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit()
                .create(APIService::class.java)
                .getQuestion(resources.getString(R.string.PHP_BASE_URL)
                        +"amount=$amount"+"&encode=base64")
            if (call.isSuccessful){
                this@Game.runOnUiThread() {
                    updateQuestion(call.body())
                }
            }else{
                this@Game.runOnUiThread() {
                    updateQuestion(null)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateQuestion(body: ResponseQuestion?) {
        if (body != null ){
            val datos = body?.result?.get(0)
            binding.txtGameMode.text =
                "Difficult: ${Utils().DB64(datos?.difficulty)}"
            binding.textQuestion.text =
                Utils().DB64(datos.question)
            val data = ArrayList<String>()
            for (i in datos?.incorrect_answers)
                data.add(Utils().DB64(i))
            data.add(Utils().DB64(datos?.correct_answer))
            shuffle(data)
            val adapter = CustomQuestionAdapter(data)
            adapter.setOnItemClickListener(object : CustomQuestionAdapter.ClickListener {
                override fun onItemClick(v: View, position: Int) {
                    if (adapter.getItem(position)
                            .equals(Utils().DB64(datos?.correct_answer))){
                        points++
                        binding.txtPoints.text = points.toString()
                        searchByAmount(1)
                    }else{
                        saveDataAndExit()
                    }
                }})
            binding.listQ.adapter = adapter
        }
    }

    private fun saveDataAndExit(){
        Utils().setData(this, points.toString(), getString(R.string.points))
        val intent = Intent(this, Home::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }
}