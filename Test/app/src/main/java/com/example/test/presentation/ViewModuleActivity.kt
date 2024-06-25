package com.example.test.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.test.R
import java.util.Calendar

class ViewModuleActivity : ViewModel() {
    lateinit var navController: NavController
    val dayTo = Calendar.getInstance()
    var dayFrom = Calendar.getInstance()
    var cityTO = "Турция"
    var cityFrom: String = "Москва"
    var bottomNavigation = 1
    var bottomAirTickets = R.id.airTicketsFragment

    private val monthText = listOf<String>(
        "января",
        "февраля",
        "мара",
        "апреля",
        "майя",
        "июня",
        "июля",
        "августа",
        "сентября",
        "октяьря",
        "ноября",
        "декабря"
    )
    private val weekText = listOf<String>(
        "вс", "пн", "вт", "ср", "чт", "пт", "сб"
    )

    fun dayStringPreview(calendar: Calendar): String {
        val monthInt = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val week = calendar.get(Calendar.DAY_OF_WEEK) - 1
        val month = monthText[monthInt]!!.filterIndexed { index, c ->
            index < 3
        }
        return "$day $month ${weekText[week]}"
    }

    fun dayStringTo(): String {
        val monthInt = dayFrom.get(Calendar.MONTH)
        val day = dayFrom.get(Calendar.DAY_OF_MONTH)
        return "$day ${monthText[monthInt]} "
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}