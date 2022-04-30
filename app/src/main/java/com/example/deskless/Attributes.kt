package com.example.deskless

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseUser

//
@ParseClassName("Attributes")
class Attributes:ParseObject() {
    //GETTERS

    fun getUserRole(): String?{
        return getString(KEY_USERROLE)
    }
    fun getBio(): String?{
        return getString(KEY_BIO)
    }
    fun getCompanyName(): String?{
        return getString(KEY_COMPANYNAME)
    }
    fun getUser(): ParseUser?{
        return getParseUser(Post.KEY_USER)
    }

    fun getImage(): ParseFile? {
        return getParseFile(KEY_IMAGE)
    }

    //SETTERS
    fun setBio(bio: String){
        put(KEY_BIO, bio)
    }
    fun setUserRole(userrole: String){
        put(KEY_USERROLE, userrole)
    }
    fun setCompanyName(companyName: String){
        put(KEY_COMPANYNAME, companyName)
    }

    fun setUser(user: ParseUser){
        put(Post.KEY_USER, user)
    }
    fun setImage(parsefile: ParseFile){
        put(KEY_IMAGE, parsefile)
    }



    companion object{
//        const val KEY_USERNAME = "username"
        const val KEY_USERROLE= "userRole"
        const val KEY_COMPANYNAME= "userCompany"
        const val KEY_BIO= "userBio"
        const val KEY_USER = "user"
        const val KEY_IMAGE = "profilePicture"

    }
}