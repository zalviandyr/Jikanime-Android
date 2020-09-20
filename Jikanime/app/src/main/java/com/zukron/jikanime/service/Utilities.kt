package com.zukron.jikanime.service

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/8/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */
object Utilities {
    fun getConnectionType(context: Context): Int {
        // Returns connection type. 0: none; 1: mobile data; 2: wifi
        var result = 0
        val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        networkCapabilities?.run {
            if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                result = 2
            } else if (hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                result = 1
            }
        }

        return result
    }

    fun concatDate(_startDate: String?, _endDate: String?): String {
        var startDate = _startDate
        var endDate = _endDate

        if (startDate == "null" || startDate == null) {
            startDate = "Unknown"
        }

        if (endDate == "null" || endDate == null) {
            endDate = "Unknown"
        }

        return "$startDate - $endDate"
    }

    fun formatDate(date: String?): String {
        if (date == "null" || date == null) {
            return "Unknown"
        }

        val parseFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+ss:ss")
        val localDateTime = LocalDateTime.parse(date, parseFormatter)
        val formatter = DateTimeFormatter.ofPattern("dd LLL yyyy")
        return localDateTime.format(formatter)
    }
}