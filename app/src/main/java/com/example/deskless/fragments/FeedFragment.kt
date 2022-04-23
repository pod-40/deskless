package com.example.deskless.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.deskless.Post

import com.example.deskless.R
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery


class FeedFragment : Fragment() {
    lateinit var postsRecyclerView: RecyclerView

    var allPosts: MutableList<Post> = mutableListOf()
    lateinit var swipeContainer: SwipeRefreshLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Set onClickListeners and Setup
        queryPosts()
    }

    //Query for all post in our server
    open fun queryPosts(){
        //Specify which class to query
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        //find all post objects
        query.include(Post.KEY_USER)
        query.setLimit(20)
        query.addDescendingOrder("createdAt")
        query.findInBackground(object : FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if (e!=null){
                    Log.e(TAG, "Error fetching posts")
                }else{
                    if (posts != null){

                        for(post in posts){
                            Log.i(TAG,"Post: "+ post.getCaption()+" . Username: "+ post.getUser()?.username)
                        }

                    }


                }
            }

        })
    }
    companion object {
        const val TAG = "FeedFragment"
    }
}