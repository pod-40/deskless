package com.example.deskless

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.parse.ParseObject
import com.parse.ParseQuery
import com.parse.ParseUser


class EditActivty : AppCompatActivity() {

//    val query: ParseQuery<Post> = ParseQuery.getQuery(Attributes::class.java)
    lateinit var btnSaveChanges: Button
    lateinit var ivProfilePicture: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)




        findViewById<Button>(R.id.btnSaveChanges).setOnClickListener {

            val userName = findViewById<EditText>(R.id.etUserName1).text.toString()
            val bio1  = findViewById<EditText>(R.id.etBio1).text.toString()
            val userRole = findViewById<EditText>(R.id.etUserRole).text.toString()
            val company = findViewById<EditText>(R.id.etCompany).text.toString()

            ivProfilePicture = findViewById(R.id.ivProfilePicture)
            val user = ParseUser.getCurrentUser()

            editProfile(user, bio1, userRole, company)

            val intent1 = Intent(this, MainActivity::class.java)
            startActivity(intent1)
        }

    }

    fun editProfile(user: ParseUser, bio: String, userRole: String, company: String){
        val attributes = Attributes()
        attributes.setUser(user)
        attributes.setBio(bio)
        attributes.setUserRole(userRole)
        attributes.setCompanyName(company)


        attributes.saveInBackground { exception ->
            if (exception != null){
                Log.e(TAG, "Error while updating profile")
                exception.printStackTrace()
                Toast.makeText(this, "couldn't save", Toast.LENGTH_SHORT).show()
            }else {
                Log.i(TAG, "Successfully updated profile")
                Toast.makeText(this, "profile updated", Toast.LENGTH_SHORT).show()
            }
        }

    }

    companion object{
        const val TAG = "EditActivity"
    }
}