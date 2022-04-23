package com.example.deskless

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.deskless.fragments.ExploreFragment
import com.example.deskless.fragments.FeedFragment
import com.example.deskless.fragments.PostFragment
import com.example.deskless.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.ParseUser

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager



        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
            item ->
            var fragmentToShow: Fragment? =null
            when(item.itemId){
                R.id.action_explore ->{
                    fragmentToShow = ExploreFragment()
                }
                R.id.action_feed ->{
                    fragmentToShow = FeedFragment()
                }
                R.id.action_profile ->{
                    fragmentToShow = ProfileFragment()
                }
                R.id.action_post ->{
                    fragmentToShow = PostFragment()
                }
            }
            if (fragmentToShow != null){
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow).commit()
            }


            true
        }

    }



    fun signOut(){
        ParseUser.logOut()
        val intent1 = Intent(this, LoginActivity::class.java)
        startActivity(intent1)
    }
}