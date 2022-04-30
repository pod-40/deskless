package com.example.deskless

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FeedAdapter(val context: Context, val posts: ArrayList<Post>) : RecyclerView.Adapter<FeedAdapter.ViewHolder>(){
    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeedAdapter.ViewHolder, position: Int) {
        val post = posts.get(position)
        holder.bind(post)
    }

    fun clear() {
        posts.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val ivImage: ImageView
        val tvCaption: TextView
        val tvLocation: TextView
        val tvVibes: TextView
        val tvUserName: TextView
        val tvAmmenities: TextView
        val tvTime: TextView
        val tvMaxNumberOfPeople: TextView
        val tvRole: TextView
        val tvCompany: TextView

        init{
            ivImage = itemView.findViewById(R.id.imageView)
            tvCaption = itemView.findViewById(R.id.tvCaption)
            tvLocation = itemView.findViewById(R.id.tvLocation)
            tvVibes = itemView.findViewById(R.id.tvVibes)
            tvUserName = itemView.findViewById(R.id.tvUserName)
            tvAmmenities = itemView.findViewById(R.id.tvAmmenities)
            tvTime = itemView.findViewById(R.id.tvTime)
            tvMaxNumberOfPeople = itemView.findViewById(R.id.tvMaxNumOfPeople)
            tvRole = itemView.findViewById(R.id.tvRole)
            tvCompany = itemView.findViewById(R.id.tvCompany)
        }

        fun bind(post: Post){
            tvCaption.text = post.getCaption()
            tvUserName.text = post.getUser()?.username
            tvLocation.text = post.getLocation()
            tvVibes.text = post.getVibe()
            tvAmmenities.text = post.getAmmenities()
            tvTime.text = post.getTime()
            tvMaxNumberOfPeople.text = post.getMaxNumOfPeople()


            Glide.with(itemView.context).load(post.getImage()?.url).into(ivImage)

        }

        //Delete if an error occurs
        fun bind(attributes: Attributes){
            tvRole.text = attributes.getUserRole()
            tvCompany.text = attributes.getCompanyName()
        }

    }
}