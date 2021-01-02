package com.moichsan.consumerapp.data.repository

class NetworkState(val message: String) {

    companion object {
        val LOADED: NetworkState = NetworkState("Success")
        val LOADING: NetworkState = NetworkState("Running")
        val EMPTY: NetworkState = NetworkState("User Not Found")
        val ERROR: NetworkState = NetworkState("Something went wrong")
    }

}