package co.com.quizzblizzpzz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.SignInButton
import androidx.core.app.ActivityCompat.startActivityForResult

import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import java.lang.Exception

class Log : AppCompatActivity(), View.OnClickListener {

    val RC_SIGN_IN : Int = 1001
    var mGoogleSignInClient : GoogleSignInClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInButton: SignInButton = findViewById(R.id.sign_in_button_google)
        signInButton.setSize(SignInButton.SIZE_STANDARD)
        signInButton.setOnClickListener(this)

    }

    override fun onStart() {
        super.onStart()
        updateUI(GoogleSignIn.getLastSignedInAccount(this), Global().RC_SIGN_IN)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.sign_in_button_google -> signInGoogle()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Global().RC_SIGN_IN){
            val task : Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try{
            val account : GoogleSignInAccount = task.getResult(ApiException::class.java)
            updateUI(account, Global().RC_SIGN_IN)
        }catch (e: ApiException){
            updateUI(null, Global().RC_SIGN_IN)
        }catch (e: Exception){
            updateUI(null, Global().RC_SIGN_IN)
        }
    }

    private fun signInGoogle(){
        val signInIntent: Intent = mGoogleSignInClient?.getSignInIntent() ?: return
        startActivityForResult(signInIntent, Global().RC_SIGN_IN)
    }

    private fun updateUI(account: GoogleSignInAccount?, code: Int) {
        val intent = Intent(this, Home::class.java)
        when (code){
            Global().RC_SIGN_IN ->  {
                if (account != null) startActivity(intent) else Utils().t(this, resources.getString(R.string.MSG_NO_LOG))
            }
        }
    }
}
