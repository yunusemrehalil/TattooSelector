package com.example.tattooselector

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        firebaseAuth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setCanceledOnTouchOutside(false)

        backButton.setOnClickListener()
        {
            onBackPressed()
        }
        registerButton.setOnClickListener()
        {
            validateData()
        }
    }
    private var name = ""
    private var mail = ""
    private var password = ""

    private fun validateData()
    {
        name = NameEt.text.toString().trim()
        mail = EmailEt.text.toString().trim()
        password = PasswordEt.text.toString().trim()

        if(name.isEmpty())
        {
            Toast.makeText(this, "Lütfen isim giriniz.", Toast.LENGTH_SHORT).show()
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches())
        {
            Toast.makeText(this, "Mailinizi tekrar giriniz", Toast.LENGTH_SHORT).show()
        }
        else if(password.isEmpty())
        {
            Toast.makeText(this, "Lütfen şifrenizi giriniz.", Toast.LENGTH_SHORT).show()
        }
        else createUserAccount()
    }

    private fun createUserAccount()
    {
        progressDialog.setMessage("Hesap oluşturuluyor...")
        progressDialog.show()

        firebaseAuth.createUserWithEmailAndPassword(mail,password)
            .addOnSuccessListener {
                updateUserInfo()
            }
            .addOnFailureListener() {
                progressDialog.dismiss()
                Toast.makeText(this, "${it.message} yüzünden başarısız oldu", Toast.LENGTH_SHORT).show()
            }
    }
    private fun updateUserInfo()
    {
        progressDialog.setMessage("Kaydediliyor...")
        val timeStamp = System.currentTimeMillis()
        val uid = firebaseAuth.uid
        val hashMap: HashMap<String,Any?> = HashMap()

        hashMap["uid"] = uid
        hashMap["email"] = mail
        hashMap["name"] = name
        hashMap["profileImage"] = ""
        hashMap["userType"] = "user"
        hashMap["timestamp"] = timeStamp

        val ref = FirebaseDatabase.getInstance().getReference("Users")
        ref.child(uid!!)
            .setValue(hashMap)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(this, "Hesap oluşturuldu", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@RegisterActivity,DashboardActivity::class.java))
                finish()
            }
            .addOnFailureListener(){
                progressDialog.dismiss()
                Toast.makeText(this, "${it.message} yüzünden kaydederken hata oluştu", Toast.LENGTH_SHORT).show()
            }

    }
}