package com.example.pulsetracker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.pulsetracker.R
import com.example.pulsetracker.data.Mode

class ModeAdapter(private val modes: List<Mode>, private val context: Context) : PagerAdapter() {

    override fun isViewFromObject(view: View, obj: Any): Boolean = view === obj

    override fun getCount(): Int = modes.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.item_mode, container, false)
        val textView = view.findViewById<TextView>(R.id.mode_name)
        val mode = modes[position]
        textView.text = mode.name
        view.setOnClickListener { mode.onClick.invoke() }

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}


