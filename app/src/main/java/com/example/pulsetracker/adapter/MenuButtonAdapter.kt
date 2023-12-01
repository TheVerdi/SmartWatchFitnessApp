package com.example.pulsetracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.pulsetracker.R
import com.example.pulsetracker.data.MenuButton

class MenuButtonAdapter(private val menuButtons: List<MenuButton>) :
    RecyclerView.Adapter<MenuButtonAdapter.ViewHolder>() {

    private var centeredPosition = -1

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val button: Button = view.findViewById(R.id.menu_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val menuButton = menuButtons[position]
        holder.button.text = menuButton.label

        val isCentered = position == centeredPosition
        holder.button.isClickable = isCentered
        holder.button.isFocusable = isCentered

        if (isCentered) {
            holder.button.setOnClickListener {
                menuButton.onClickListener.invoke()
            }
        } else {
            holder.button.setOnClickListener(null)
        }
    }

    override fun getItemCount() = menuButtons.size

    fun setCenteredPosition(position: Int) {
        val oldPosition = centeredPosition
        centeredPosition = position

        notifyItemChanged(oldPosition)
        notifyItemChanged(centeredPosition)
    }
}
