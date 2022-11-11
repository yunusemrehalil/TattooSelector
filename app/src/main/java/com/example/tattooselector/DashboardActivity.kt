package com.example.tattooselector

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_dashboard.*

private lateinit var firebaseAuth: FirebaseAuth

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

    }
    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null) {
            //startActivity(Intent(this@DashboardActivity,MainActivity::class.java))
            //finish()
            dashboardTV.text = "Yunus"
        } else {
            val mail = firebaseUser.email
            dashboardTV.text = mail.toString()
        }
    }
}
