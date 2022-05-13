package co.com.quizzblizzpzz

import android.content.Context
import android.widget.Toast

class Utils {

    /*
        fun t: nos imprime un mensaje dado el contexto

     */
    fun t(context: Context, msg : String){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}