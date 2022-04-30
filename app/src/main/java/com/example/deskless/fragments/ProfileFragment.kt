package com.example.deskless.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deskless.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

class ProfileFragment : Fragment() {

    lateinit var profileRecyclerView: RecyclerView
    lateinit var adapter: AttributesAdapter
    var allAttributes: MutableList<Attributes> = mutableListOf()

    //For the Post on the profile page
    lateinit var postRecyclerView: RecyclerView
    lateinit var postAdapter: PostAdapter
    var allPosts: MutableList<Post> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<Button>(R.id.btnLogOut1).setOnClickListener {
            //Launch camera to let your user take a picture
            signOut()
        }

        view.findViewById<FloatingActionButton>(R.id.btnEdit).setOnClickListener {
            //Launch the user edit page
            val intent = Intent(requireContext(), EditActivty::class.java)
            startActivity(intent)
        }

        profileRecyclerView = view.findViewById(R.id.rvProfile)
        adapter = AttributesAdapter(requireContext(), allAttributes)
        profileRecyclerView.adapter = adapter
        profileRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        queryProfile()

        //for the post on the profile page
        postRecyclerView = view.findViewById(R.id.rvUserPost)
        postAdapter = PostAdapter(requireContext(), allPosts as ArrayList<Post>)
        postRecyclerView.adapter = postAdapter
        postRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        queryPosts()


    }

    fun signOut(){
        ParseUser.logOut()
        val intent1 = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent1)
    }

    //Query for all post in our server
    fun queryProfile(){
        //Specify which class to query
        val query: ParseQuery<Attributes> = ParseQuery.getQuery(Attributes::class.java)
        //find all post objects
        query.include(Attributes.KEY_USER)

        query.setLimit(1)
        query.addDescendingOrder("createdAt")
        query.findInBackground(object : FindCallback<Attributes> {
            override fun done(attributes: MutableList<Attributes>?, e: ParseException?) {
                if (e!=null){
                    Log.e(TAG, "Error fetching posts")
                }else{
                    if (attributes != null){

                        for(attribute in attributes){
                            Log.i(TAG,"Attribute: "+ attribute.getBio()+" . Username: "+ attribute.getUser()?.username + "userRole" + attribute.getUserRole())
                        }
                        allAttributes.addAll(attributes)
                        adapter.notifyDataSetChanged()
                    }


                }
            }

        })
    }

    open fun queryPosts(){
        //Specify which class to query
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        //find all post objects
        query.include(Post.KEY_USER)
        query.whereEqualTo(Attributes.KEY_USER, ParseUser.getCurrentUser()).first
        query.setLimit(20)
        query.addDescendingOrder("createdAt")
        query.findInBackground(object : FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if (e!=null){
                    Log.e(FeedFragment.TAG, "Error fetching posts")
                }else{
                    if (posts != null){
                        postAdapter.clear()
                        for(post in posts){
                            Log.i(FeedFragment.TAG,"Post: "+ post.getCaption()+" . Username: "+ post.getUser()?.username)
                        }

                        allPosts.addAll(posts)
                        postAdapter.notifyDataSetChanged()


                    }


                }
            }

        })
    }

    companion object {
        const val TAG = "ProfileFragment"
    }
}