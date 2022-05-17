package co.com.quizzblizzpzz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import co.com.quizzblizzpzz.databinding.ActivityHomeBinding
import co.com.quizzblizzpzz.utils.Utils

class Home : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtVersion.setText(BuildConfig.VERSION_NAME)
        binding.btnBeginGame.setOnClickListener(this)
        binding.txtHomePoints.text = Utils().getData(this, getString(R.string.points), "0")

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btnBeginGame -> {
                val intent = Intent(this, Game::class.java)
                startActivity(intent)
            }
        }
    }
}