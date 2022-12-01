package com.example.tattooselector


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tattooselector.Adapter.ViewPagerAdapter
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_main.*
import com.example.tattooselector.Listener.IFirebaseLoadDone

class MainActivity : AppCompatActivity() {

    lateinit var adapter: ViewPagerAdapter
    lateinit var tattoos:DatabaseReference

    lateinit var iFirebaseLoadDone: IFirebaseLoadDone
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login_button.setOnClickListener()
        {
            startActivity(Intent(this,LoginActivity::class.java))
        }
        skip_button.setOnClickListener()
        {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }

}