package com.example.swipe.utils

import java.text.FieldPosition

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition:Int, toPosition:Int)
    fun onItemDismiss(position:Int)
}