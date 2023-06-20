package com.example.pulsetracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.pulsetracker.adapter.MenuButtonAdapter
import com.example.pulsetracker.data.MenuButton
import com.mig35.carousellayoutmanager.CarouselLayoutManager
import com.mig35.carousellayoutmanager.CarouselZoomPostLayoutListener

class MainActivity : AppCompatActivity() {

    private lateinit var menuRecyclerView: RecyclerView
    private lateinit var menuButtonAdapter: MenuButtonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        menuRecyclerView = findViewById(R.id.recycler_view)

        val menuButtons = listOf(
            MenuButton("Pulse", "Pulse") {
                val intent = Intent(this, PulsometerActivity::class.java)
                startActivity(intent)
            },
            MenuButton("Button 2", "Button 2") {
                val intent = Intent(this, PulsometerActivity::class.java)
                startActivity(intent)
            },
            MenuButton("Button 3","Button 3" ) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            },
            MenuButton("Button 4","Button 4" ) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            },
            MenuButton("Button 5","Button 5" ) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            },
            MenuButton("Button 6","Button 6" ) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        )

        menuButtonAdapter = MenuButtonAdapter(menuButtons)
        menuRecyclerView.adapter = menuButtonAdapter

        val layoutManager = CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL)
        layoutManager.setPostLayoutListener(CarouselZoomPostLayoutListener())
        menuRecyclerView.layoutManager = layoutManager
        menuRecyclerView.setHasFixedSize(true)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(menuRecyclerView)

        menuRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val centeredView = snapHelper.findSnapView(layoutManager)
                    centeredView?.let {
                        val position = layoutManager.getPosition(it)
                        menuButtonAdapter.setCenteredPosition(position)
                    }
                }
            }
        })
    }
}
