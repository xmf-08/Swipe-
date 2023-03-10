package com.example.swipe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.UserHandle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.swipe.adapters.UserAdapter
import com.example.swipe.databinding.ActivityMainBinding
import com.example.swipe.models.User
import com.example.swipe.utils.ItemTouchHelperAdapter

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var list:ArrayList<User>
    lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        userAdapter = UserAdapter(this, list)
        binding.rv.adapter = userAdapter

        rvActions()
    }

    private fun rvActions() {
         val itemTouchHelper = object : ItemTouchHelper.Callback(){
             override fun getMovementFlags(
                 recyclerView: RecyclerView,
                 viewHolder: RecyclerView.ViewHolder
             ): Int {
                 val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                 val swipeFlags =  ItemTouchHelper.END
                 return makeMovementFlags(dragFlags,swipeFlags)
             }

             override fun onMove(
                 recyclerView: RecyclerView,
                 viewHolder: RecyclerView.ViewHolder,
                 target: RecyclerView.ViewHolder
             ): Boolean {
                 userAdapter.onItemMove(viewHolder.adapterPosition,target.adapterPosition)
                 return true
             }

             override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
             }
             fun delete(viewHolder: RecyclerView.ViewHolder,direction: Int){
                 userAdapter.onItemDismiss(viewHolder.adapterPosition)
             }
         }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.rv)
    }

    private fun loadData() {
            list = ArrayList()
            list.add(User( "https://i1.sndcdn.com/artworks-000562103676-ygjh1u-t500x500.jpg"))
            list.add(User("https://images.genius.com/7e73f3a0eb6e93c41e89e3eab9f3c45f.1000x1000x1.jpg"))
            list.add(User("https://i1.sndcdn.com/artworks-s3zOCWcV8XQVtQcv-0emq8A-t500x500.jpg"))
            list.add(User("https://i.ytimg.com/vi/1DoI5WTjd3w/maxresdefault.jpg"))
            list.add(User("https://i.scdn.co/image/ab67616d0000b273da6f73a25f4c79d0e6b4a8bd"))
            list.add(User( "https://i.ytimg.com/vi/oALvLDSFuZQ/hqdefault.jpg"))
    }
}