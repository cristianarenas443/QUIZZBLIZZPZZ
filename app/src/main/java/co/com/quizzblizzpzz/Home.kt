package co.com.quizzblizzpzz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import co.com.quizzblizzpzz.databinding.ActivityGameBinding
import co.com.quizzblizzpzz.databinding.ActivityHomeBinding

class Home : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtVersion.setText(BuildConfig.VERSION_NAME)
        binding.btnBeginGame.setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btnBeginGame -> {
                Toast.makeText(this, "*** pailas", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Game::class.java)
                startActivity(intent)
            }
        }
    }
}