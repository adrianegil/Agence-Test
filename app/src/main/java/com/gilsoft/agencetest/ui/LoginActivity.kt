package com.gilsoft.agencetest.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.gilsoft.agencetest.MainActivity
import com.gilsoft.agencetest.databinding.ActivityLoginBinding
import com.gilsoft.agencetest.entity.User
import com.gilsoft.agencetest.util.UserHelper
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import org.json.JSONException


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var callbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (UserHelper.isLogIn(this)) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }

        binding.buttonLogInNormal.setOnClickListener {
            logInNormal()
        }

        binding.buttonLogInFacebook.setOnClickListener {
            logInFacebook()
        }

        binding.buttonLogInGoogle.setOnClickListener {
            logInGoogle()
        }
    }

    fun logInNormal() {
        UserHelper.saveUserData(User("User LogIn Normal", "userloginnormal@login.com"), this)
        UserHelper.changeLogInStatus(true, this)
        UserHelper.saveAutenticationType("Normal", this)
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
        Toast.makeText(
            applicationContext,
            "Autenticación Exitosa Normal",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun logInFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(this, listOf("public_profile", "email"))
        LoginManager.getInstance()
            .registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
                override fun onSuccess(result: LoginResult?) {
                    val request = GraphRequest.newMeRequest(
                        result?.accessToken
                    ) { `object`, response ->
                        try {
                            UserHelper.saveUserData(
                                User(`object`.getString("name"), `object`.getString("email")),
                                this@LoginActivity
                            )

                        } catch (e: JSONException) {
                            UserHelper.saveUserData(
                                User(`object`.getString("name"), ""),
                                this@LoginActivity
                            )
                            e.printStackTrace()
                        }
                        UserHelper.changeLogInStatus(true, this@LoginActivity)
                        UserHelper.saveAutenticationType("Facebook", this@LoginActivity)
                        //LoginManager.getInstance().logOut()
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                        Toast.makeText(
                            applicationContext,
                            "Autenticación Exitosa mediante Facebook",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    val parameters = Bundle()
                    parameters.putString("fields", "id,name,link,email")
                    request.parameters = parameters
                    request.executeAsync()

                }

                override fun onCancel() {
                    Toast.makeText(
                        applicationContext,
                        "Autenticación Cancelada",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onError(error: FacebookException?) {
                    Toast.makeText(
                        applicationContext,
                        "Error de Autenticación",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

    }

    fun logInGoogle() {
        val googleConf =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail()
                .build()
        val googleClient = GoogleSignIn.getClient(this, googleConf)
        googleClient.signOut()
        val intentGoogle = googleClient.signInIntent
        startActivityForResult(intentGoogle, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    UserHelper.saveUserData(
                        User(
                            account.displayName.toString(),
                            account.email.toString()
                        ), this
                    )
                    UserHelper.changeLogInStatus(true, this)
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                    Toast.makeText(
                        applicationContext,
                        "Autenticación Exitosa mediante Google",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: ApiException) {
                Toast.makeText(this, "Error en la Autenticación", Toast.LENGTH_SHORT).show()
            }

        }
    }
}