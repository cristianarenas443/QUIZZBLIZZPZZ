package co.com.quizzblizzpzz

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.provider.Settings.Global.getString
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.util.*
import kotlin.collections.ArrayList

class Utils {

    /*
       fun t: nos imprime un mensaje dado el contexto
    */
    fun t(context: Context, msg : String){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    /*
       fun t: nos imprime un mensaje dado el contexto
    */
    fun setData(activity: Activity, data: String, key: String){
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()){
            putString(key, data)
            apply()
        }
    }

    /*
       fun getData: nos devuelve la información del puntaje
    */
    fun getData(activity: Activity, key: String, defaultValue: String) : String{
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return defaultValue
        return sharedPref.getString(key, defaultValue).toString()
    }

    /*
       fun t: nos imprime un mensaje dado el contexto
    */
    @RequiresApi(Build.VERSION_CODES.O)
    fun DB64(dataCoded : String): String{
        return Base64.getDecoder().decode(dataCoded).toString()
    }
}