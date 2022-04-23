package com.example.deskless.fragments

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.deskless.LoginActivity.Companion.TAG
import com.example.deskless.MainActivity
import com.example.deskless.Post
import com.example.deskless.R
import com.example.deskless.fragments.FeedFragment.Companion.TAG
import com.parse.ParseFile
import com.parse.ParseUser
import java.io.File

class PostFragment : Fragment() {
    val photoFileName = "photo.jpg"
    var photoFile: File? = null
    val CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034
    lateinit var ivPreview: ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Set onClickListeners and Setup
        ivPreview = view.findViewById(R.id.ivImage)

        val ivPreview: ImageView = view.findViewById(R.id.ivImage)

        view.findViewById<Button>(R.id.btnCreatePost).setOnClickListener {
            //send post to the sever
            val caption = view.findViewById<EditText>(R.id.etCaption).text.toString()
            val location = view.findViewById<EditText>(R.id.etLocation).text.toString()
            val ammenities = view.findViewById<EditText>(R.id.etAmmenities).text.toString()
            val time= view.findViewById<EditText>(R.id.etTime).text.toString()
            val maxNumOfPeople = view.findViewById<EditText>(R.id.etMaxAmountOfPeople).text.toString()
            val vibe= view.findViewById<EditText>(R.id.etVibe).text.toString()
            val user = ParseUser.getCurrentUser()


            if (photoFile != null) {
                submitPost(caption, user, location, ammenities, time, vibe, photoFile!!)
            }else{

            }


        }

        view.findViewById<Button>(R.id.btnAddImage).setOnClickListener {
            //Launch camera to let your user take a picture
            onLaunchCamera()
        }

    }



    fun submitPost(caption:String, user: ParseUser, location:String, ammenities:String, time:String, vibe:String, file: File){
        val post = Post()
        post.setCaption(caption)
        post.setVibe(vibe)
        post.setTime(time)
        post.setAmmenities(ammenities)
        post.setUser(user)
        post.setLocation(location)

        post.saveInBackground { exception ->
            if (exception != null){
                Log.e(TAG, "Error while saving post")
                exception.printStackTrace()
            }else {
                Log.i(TAG, "Successfully saved post")
            }
        }
        Toast.makeText(requireContext(), "Post Submitted", Toast.LENGTH_SHORT).show()
    }

    fun onLaunchCamera() {
        // create Intent to take a picture and return control to the calling application
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Create a File reference for future access
        photoFile = getPhotoFileUri(photoFileName)

        // wrap File object into a content provider
        // required for API >= 24
        // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
        if (photoFile != null) {
            val fileProvider: Uri =
                FileProvider.getUriForFile(requireContext(), "com.codepath.fileprovider", photoFile!!)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)

            // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
            // So as long as the result is not null, it's safe to use the intent.

            // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
            // So as long as the result is not null, it's safe to use the intent.
            if (intent.resolveActivity(requireContext().packageManager) != null) {
                // Start the image capture intent to take photo
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE)
            }
        }
    }

    // Returns the File for a photo stored on disk given the fileName
    fun getPhotoFileUri(fileName: String): File {
        // Get safe storage directory for photos
        // Use `getExternalFilesDir` on Context to access package-specific directories.
        // This way, we don't need to request external read/write runtime permissions.
        val mediaStorageDir =
            File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG)

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            Log.d(TAG, "failed to create directory")
        }

        // Return the file target for the photo based on filename
        return File(mediaStorageDir.path + File.separator + fileName)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                // by this point we have the camera photo on disk
                val takenImage = BitmapFactory.decodeFile(photoFile!!.absolutePath)
                // RESIZE BITMAP, see section below
                // Load the taken image into a preview

                ivPreview.setImageBitmap(takenImage)
            } else { // Result was a failure
                Toast.makeText(requireContext(), "Picture wasn't taken!", Toast.LENGTH_SHORT).show()
            }
        }
    }


    companion object{
        const val TAG = "MainActivity"
    }
}