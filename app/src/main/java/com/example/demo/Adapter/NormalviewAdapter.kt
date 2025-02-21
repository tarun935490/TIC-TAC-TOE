package com.example.demo.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.demo.R
import java.util.ArrayList

class NormalviewAdapter(private val context: Context,private val imagelist:ArrayList<Int>) :PagerAdapter(){
    override fun getCount(): Int {
        return imagelist.size
    }

    override fun isViewFromObject(view: View, `object`: Any) = view == `object`
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val item = LayoutInflater.from(context).inflate(R.layout.viewpager,container,false)
        val imageView:ImageView = item.findViewById(R.id.imagepager)
        imageView.setImageResource(imagelist[position])
        container.addView(item)
        return item
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as CardView)
    }
}