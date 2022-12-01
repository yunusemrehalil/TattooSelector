package com.example.tattooselector.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tattooselector.Model.Tattoo
import com.example.tattooselector.R

class ViewPagerAdapter(private var title:List<String>, private var details:List<String>, private var images: MutableList<Tattoo>):RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>()
{

    inner class Pager2ViewHolder(itemview: View): RecyclerView.ViewHolder(itemview)
    {

        val itemTitle: TextView = itemview.findViewById(R.id.tattoo_baslik)
        val itemDetail: TextView = itemview.findViewById(R.id.tattoo_icerik)
        val itemImage: ImageView = itemview.findViewById(R.id.tattoo_image)

        init {
            itemImage.setOnClickListener()
            {
                val position = adapterPosition
                Toast.makeText(itemview.context,"#${position+1}. gorsele tikladiniz", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Pager2ViewHolder
    {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_page,parent,false))
    }

    override fun getItemCount(): Int {
        return title.size
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        holder.itemTitle.text = title[position]
        holder.itemDetail.text = details[position]
        //holder.itemImage.setImageResource([position])
    }
}