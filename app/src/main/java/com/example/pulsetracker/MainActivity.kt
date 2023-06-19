package com.example.pulsetracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.pulsetracker.adapter.MenuButtonAdapter
import com.example.pulsetracker.data.MenuButton
import com.mig35.carousellayoutmanager.CarouselLayoutManager
import com.mig35.carousellayoutmanager.CarouselZoomPostLayoutListener
import com.mig35.carousellayoutmanager.CenterScrollListener

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)

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

        recyclerView.adapter = MenuButtonAdapter(menuButtons)


        val layoutManager = CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL)
        layoutManager.setPostLayoutListener(CarouselZoomPostLayoutListener())
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.addOnScrollListener(CenterScrollListener())

    }
}
