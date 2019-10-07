package carlos.nicolau.galves.androidcwb.framework.util

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkInfo

class InternetUtilsImpl(val context: Context) : InternetUtils {
    override fun isNetworkAvailable(): Boolean {
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }
}

interface InternetUtils {
    fun isNetworkAvailable() : Boolean
}