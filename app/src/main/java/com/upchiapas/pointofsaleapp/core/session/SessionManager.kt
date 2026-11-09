package com.upchiapas.pointofsaleapp.core.session

import android.content.Context
import android.content.SharedPreferences
class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    //guardar token
    fun saveToken(token: String) {
        prefs.edit().putString("jwt_token", token).apply()
    }

    fun getToken(): String? {
        return prefs.getString("jwt_token", null)
    }

    fun clearToken() {
        prefs.edit().remove("jwt_token").apply()
    }
}