package com.moichsan.githubusers.data.repository

class NetState(val message: String) {

    companion object {
        val LOADED: NetState = NetState("Success")
        val LOADING: NetState = NetState("Running")
        val EMPTY: NetState = NetState("User Not Found")
        val ERROR: NetState = NetState("Something went wrong")
    }

}