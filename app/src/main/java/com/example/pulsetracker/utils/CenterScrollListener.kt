import androidx.recyclerview.widget.RecyclerView
import com.example.pulsetracker.adapter.MenuButtonAdapter
import com.mig35.carousellayoutmanager.CarouselLayoutManager

class CenterScrollListener(private val adapter: MenuButtonAdapter) : RecyclerView.OnScrollListener() {

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            val layoutManager = recyclerView.layoutManager as? CarouselLayoutManager
            val centerItemPosition = layoutManager?.centerItemPosition ?: -1

            adapter.setCenteredPosition(centerItemPosition)
        }
    }
}
