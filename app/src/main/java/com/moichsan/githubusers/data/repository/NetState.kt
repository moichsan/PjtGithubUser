package com.moichsan.githubusers.data.repository

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

class NetState(val status: Status, val message: String) {

    companion object {
        val LOADED: NetState = NetState(Status.SUCCESS, "Success")
        val LOADING: NetState = NetState(Status.RUNNING, "Running")
        val EMPTY: NetState = NetState(Status.RUNNING, "User Not Found")
        val ERROR: NetState = NetState(Status.FAILED, "Something went wrong")
        val ENDOFLIST: NetState = NetState(Status.FAILED, "You have Reached the end")
    }

}