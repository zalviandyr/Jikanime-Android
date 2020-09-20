package com.zukron.jikanime.network

/**
 * Project name is Jikanime
 * Created by Zukron Alviandy R on 9/11/2020
 * Contact me if any issues on zukronalviandy@gmail.com
 */

class NetworkState(val message: String) {

    companion object {
        val LOADED = NetworkState("Success getting data")
        val LOADING = NetworkState("Loading")
        val ERROR = NetworkState("Something went wrong")
        val TIMEOUT = NetworkState("Slow connection")
        val NO_CONTENT = NetworkState("No content available")
        val NO_CONNECTION = NetworkState("No internet connection")
    }
}
