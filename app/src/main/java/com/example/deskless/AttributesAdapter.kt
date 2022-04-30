package com.example.deskless

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AttributesAdapter(val context: Context, val attributes: List<Attributes>): RecyclerView.Adapter<AttributesAdapter.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AttributesAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_profile, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AttributesAdapter.ViewHolder, position: Int) {
        val attribute = attributes.get(position)
        holder.bind(attribute)
    }

    override fun getItemCount(): Int {
        return attributes.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvProfileName: TextView
        val tvUserRole: TextView
        val tvUserCompany: TextView
        val tvUserBio: TextView
        val ivProfilePicture: ImageView

        init {
            tvProfileName = itemView.findViewById(R.id.tvProfileName)
            tvUserRole = itemView.findViewById(R.id.tvUserRole)
            tvUserCompany = itemView.findViewById(R.id.tvUserCompany)
            tvUserBio = itemView.findViewById(R.id.tvUserBio)
            ivProfilePicture = itemView.findViewById(R.id.ivProfilePicture)
        }

        fun bind(attributes: Attributes){
            tvProfileName.text = attributes.getUser()?.username
            tvUserBio.text = attributes.getBio()
            tvUserCompany.text = attributes.getCompanyName()
            tvUserRole.text = attributes.getUserRole()
            Glide.with(itemView.context).load(attributes.getImage()?.url).into(ivProfilePicture)

        }
    }
}