package com.example.deskless

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseUser
import java.util.*

//Caption, Ammenities, vibe, time, Image, MaxNumOfPeople, user, location,
@ParseClassName("Post")
class Post:ParseObject() {
    //GETTERS
    fun getCaption(): String?{
        return getString(KEY_CAPTION)
    }
    fun getLocation(): String?{
        return getString(KEY_LOCATION)
    }
    fun getAmmenities(): String?{
        return getString(KEY_AMMENITIES)
    }
    fun getTime(): String?{
        return getString(KEY_TIME)
    }
    fun getVibe(): String?{
        return getString(KEY_VIBE)
    }
    fun getMaxNumOfPeople(): String?{
        return getString(KEY_MAXNUMOFPEOPLE)
    }
    fun getImage(): ParseFile?{
        return getParseFile(KEY_IMAGE)
    }
    fun getUser(): ParseUser?{
        return getParseUser(KEY_USER)
    }

    //SETTERS
    fun setCaption(caption: String){
        put(KEY_CAPTION, caption)
    }
    fun setLocation(location: String){
        put(KEY_LOCATION, location)
    }
    fun setAmmenities(ammenities: String){
        put(KEY_AMMENITIES, ammenities)
    }
    fun setTime(time: String){
        put(KEY_TIME, time)
    }
    fun setVibe(vibe: String){
        put(KEY_VIBE, vibe)
    }
    fun setMaxNumOfPeople(maxNumberOfPeople: String){
        put(KEY_MAXNUMOFPEOPLE, maxNumberOfPeople)
    }
    fun setUser(user: ParseUser){
        put(KEY_USER, user)
    }
    fun setImage(image: ParseFile){
        put(KEY_IMAGE, image)
    }



    companion object{
        const val KEY_CAPTION = "caption"
        const val KEY_LOCATION= "location"
        const val KEY_AMMENITIES = "ammenities"
        const val KEY_TIME = "time"
        const val KEY_IMAGE = "image"
        const val KEY_MAXNUMOFPEOPLE = "maxNumOfPeople"
        const val KEY_USER = "user"
        const val KEY_VIBE = "vibe"
    }
}