package com.example.swipe.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.swipe.databinding.ItemRvBinding
import com.example.swipe.models.User
import com.example.swipe.utils.ItemTouchHelperAdapter
import com.squareup.picasso.Picasso
import java.util.Collections

class UserAdapter(val context: Context, val list:ArrayList<User>) : RecyclerView.Adapter<UserAdapter.Vh>(),ItemTouchHelperAdapter {
    inner class Vh(val itemRvBinding: ItemRvBinding):RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onBind(user: User, position: Int) {
            Picasso.get().load(user.img).into(itemRvBinding.itemImg)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition > toPosition){
            for (i in fromPosition until toPosition){
                Collections.swap(list,i, i+1 )
            }
        }else{
            for (i in fromPosition until toPosition-1){
                Collections.swap(list,i,i-1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }
}