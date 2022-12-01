package com.example.tattooselector

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.tattooselector.Adapter.ViewPagerAdapter
import com.example.tattooselector.Listener.IFirebaseLoadDone
import com.example.tattooselector.Model.Tattoo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_dashboard.*
import me.relex.circleindicator.CircleIndicator3

private var titlesList = mutableListOf<String>()
private var descList = mutableListOf<String>()
//private var imageList = mutableListOf<Int>()
private var tattoos = mutableListOf<Tattoo>()
private lateinit var firebaseAuth: FirebaseAuth

class DashboardActivity : AppCompatActivity(), IFirebaseLoadDone {

    lateinit var iFirebaseLoadDone: IFirebaseLoadDone
    lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        firebaseAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("Tattoos")

        iFirebaseLoadDone = this
        //loadTattoo()
        checkUser()
        postToList()
        view_pager2.adapter = ViewPagerAdapter(titlesList, descList, tattoos)
        view_pager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val indicator: CircleIndicator3 = findViewById(R.id.indicator)
        indicator.setViewPager(view_pager2)
    }
    private fun checkUser()
    {
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser == null){
            startActivity(Intent(this@DashboardActivity,MainActivity::class.java))
        }
    }

    private fun addToList(title: String, describtion:String, images: Tattoo)
    {
        titlesList.add(title)
        descList.add(describtion)
        tattoos.add(images)
    }
    private fun postToList(){
        /*for (tattooSnapShot in snapsh)
        {
            addToList("Dovme $i", "Aciklama $i", R.mipmap.ic_launcher_round)
        }*/
        databaseReference.addListenerForSingleValueEvent(object:ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                for(tattooSnapShot in snapshot.children)
                {
                    var i:Int = 1
                    val tattoo = tattooSnapShot.getValue(Tattoo::class.java)
                    tattoos.add(tattoo!!)
                    addToList("Dovme $i", "Aciklama $i", tattoo)
                    i++
                }
            }
            override fun onCancelled(error: DatabaseError) {
                iFirebaseLoadDone.onTattooLoadFailed(error.message)
            }
        })
    }
    /*private fun loadTattoo()
    {
        tattoos.addListenerForSingleValueEvent(object:ValueEventListener{

            var tattoos:MutableList<Tattoo> = ArrayList()

            override fun onDataChange(snapshot: DataSnapshot) {
                for(tattooSnapShot in snapshot.children)
                {
                    val tattoo = tattooSnapShot.getValue(Tattoo::class.java)
                    tattoos.add(tattoo!!)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                iFirebaseLoadDone.onTattooLoadFailed(error.message)
            }
        })

    }*/
}

