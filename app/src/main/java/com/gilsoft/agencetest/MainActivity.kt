package com.gilsoft.agencetest

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.facebook.login.LoginManager
import com.gilsoft.agencetest.databinding.ActivityMainBinding
import com.gilsoft.agencetest.ui.LoginActivity
import com.gilsoft.agencetest.util.UserHelper
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var textViewUserName: TextView
    private lateinit var textViewUserEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)


        configAppBar()
        initDrawerData()

    }

    private fun configAppBar() {
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_productsFragment, R.id.nav_perfil, R.id.nav_myproducts, R.id.nav_settings
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_close -> {
                    logOut()
                }
                else -> {
                    NavigationUI.onNavDestinationSelected(
                        item,
                        Navigation.findNavController(this, R.id.nav_host_fragment_content_main)
                    )
                    binding.drawerLayout.close()
                }

            }
            false
        }
    }

    private fun initDrawerData() {
        val view1 = binding.navView.getHeaderView(0)
        var textViewUserName = view1.findViewById<TextView>(R.id.userName)
        var textViewUserEmail = view1.findViewById<TextView>(R.id.userEmail)
        textViewUserName.text = UserHelper.getUserData(this)?.name ?: ""
        textViewUserEmail.text = UserHelper.getUserData(this)?.email ?: ""
        //textViewUserEmail.text = UserHelper.getUserData(this)!!.email

    }

    private fun logOut() {
        when (UserHelper.getAutenticationType(this)) {
            "Normal" -> {
                UserHelper.changeLogInStatus(false, this)
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }

            "Facebook" -> {
                LoginManager.getInstance().logOut()
                UserHelper.changeLogInStatus(false, this)
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }
           //Google
            else -> {
                val googleConf =
                    GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail()
                        .build()
                val googleClient = GoogleSignIn.getClient(this, googleConf)
                googleClient.signOut().addOnCompleteListener {
                    UserHelper.changeLogInStatus(false, this)
                    startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    finish()
                }

            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}