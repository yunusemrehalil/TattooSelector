package com.example.tattooselector


import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import me.relex.circleindicator.CircleIndicator3
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        login_button.setOnClickListener()
        {
            startActivity(Intent(this,LoginActivity::class.java))
        }
        skip_button.setOnClickListener()
        {
            startActivity(Intent(this,DashboardActivity::class.java))
        }
    }

}