package com.example.tattooselector

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Lütfen Bekleyiniz")
        progressDialog.setCanceledOnTouchOutside(false)
        RegisterLogin.setOnClickListener()
        {
            startActivity(Intent(this@LoginActivity,RegisterActivity::class.java))
        }
        buttonLogin.setOnClickListener()
        {
            validateData()
        }
    }

    private var mail = ""
    private var password = ""
    private fun validateData()
    {
        mail = ETMail.text.toString().trim()
        password = ETPassword.text.toString().trim()

        if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches())
        {
            Toast.makeText(this, "Geçersiz mail adresi", Toast.LENGTH_SHORT).show()
        }
        else if(password.isEmpty())
        {
            Toast.makeText(this, "Lütfen şifrenizi girin.", Toast.LENGTH_SHORT).show()
        }
    }
}