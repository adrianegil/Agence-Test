package com.gilsoft.agencetest.util

import android.content.Context
import com.gilsoft.agencetest.entity.User

class UserHelper {

    companion object {
        fun saveUserData(user: User, context: Context) {
            val preferences = context.getSharedPreferences("autentication", Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString("userName", user.name)
            editor.putString("userEmail", user.email)
            editor.apply()
        }

        fun getUserData(context: Context): User? {
            val preferences = context.getSharedPreferences("autentication", Context.MODE_PRIVATE)
            return User(
                preferences.getString("userName", "").toString(),
                preferences.getString("userEmail", "").toString()
            )
        }

        fun saveAutenticationType(type: String, context: Context) {
            val preferences = context.getSharedPreferences("autentication", Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString("authType", type)
            editor.apply()
        }

        fun getAutenticationType(context: Context):String {
            val preferences = context.getSharedPreferences("autentication", Context.MODE_PRIVATE)
            return preferences.getString("authType", "").toString()
        }

        fun isLogIn(context: Context): Boolean {
            val preferences = context.getSharedPreferences("autentication", Context.MODE_PRIVATE)
            return preferences.getBoolean("isLogIn", false)
        }

        fun changeLogInStatus(satus: Boolean, context: Context) {
            val preferences = context.getSharedPreferences("autentication", Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putBoolean("isLogIn", satus)
            editor.apply()
        }

    }

}