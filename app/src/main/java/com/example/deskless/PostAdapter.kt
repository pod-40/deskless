package com.example.deskless

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PostAdapter(val context: Context, val posts: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
        return PostAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
        val post = posts.get(position)
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun clear() {
        posts.clear()
        notifyDataSetChanged()
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val ivPostImage: ImageView
        val tvPostCaption: TextView
        val tvPostLocation: TextView
        val tvPostVibes: TextView
        val tvPostUsername: TextView
        val tvPostAmmenities: TextView
        val tvPostTime: TextView
        val tvPostMaxNumberOfPeople: TextView

        init{
            ivPostImage = itemView.findViewById(R.id.ivPostImage)
            tvPostCaption = itemView.findViewById(R.id.tvPostCaption)
            tvPostLocation = itemView.findViewById(R.id.tvPostLocation)
            tvPostVibes = itemView.findViewById(R.id.tvPostVibes)
            tvPostUsername = itemView.findViewById(R.id.tvPostUsername)
            tvPostAmmenities = itemView.findViewById(R.id.tvPostAmmenities)
            tvPostTime = itemView.findViewById(R.id.tvPostTime)
            tvPostMaxNumberOfPeople = itemView.findViewById(R.id.tvPostMaxNumOfPeople)
        }

        fun bind(post: Post){
            tvPostCaption.text = post.getCaption()
            tvPostUsername.text = post.getUser()?.username
            tvPostLocation.text = post.getLocation()
            tvPostVibes.text = post.getVibe()
            tvPostAmmenities.text = post.getAmmenities()
            tvPostTime.text = post.getTime()
            tvPostMaxNumberOfPeople.text = post.getMaxNumOfPeople()


            Glide.with(itemView.context).load(post.getImage()?.url).into(ivPostImage)

        }


    }



}