package com.example.tattooselector

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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
        else {
            userLogin()
        }
    }
    private fun userLogin()
    {
        progressDialog.setMessage("Giriş yapılıyor")
        progressDialog.show()

        firebaseAuth.signInWithEmailAndPassword(mail,password)
            .addOnSuccessListener {
                checkUser()
            }
            .addOnFailureListener(){
                progressDialog.dismiss()
                Toast.makeText(this, "${it.message} sebebiyle giriş yapılamadı", Toast.LENGTH_SHORT).show()
            }
    }
    private fun checkUser()
    {
        progressDialog.setTitle("Kontrol ediliyor")

        val firebaseUser = firebaseAuth.currentUser!!
        val ref = FirebaseDatabase.getInstance().getReference("Users")
        ref.child(firebaseUser.uid)
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    TODO("Not yet implemented")
                }
            })
    }
}