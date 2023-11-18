import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.pulsetracker.R

data class TrainingInfo(val type: String, val difficulty: String, val count: Int)

class TrainingListAdapter(private val trainingData: LinkedHashMap<String, List<TrainingInfo>>) :
    BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return trainingData.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return trainingData.values.toList()[groupPosition].size
    }

    override fun getGroup(groupPosition: Int): Any {
        return trainingData.keys.toList()[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return trainingData.values.toList()[groupPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        // Implement this to create the view for the group header
        // Example: Create a TextView to display the training type
        val trainingType = getGroup(groupPosition) as String
        val textView = TextView(parent?.context)
        textView.text = trainingType
        textView.textSize = 18f
        textView.setTypeface(null, Typeface.BOLD)
        return textView
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        // Implement this to create the view for the child item
        // Example: Create a layout with TextViews for difficulty and count
        val trainingInfo = getChild(groupPosition, childPosition) as TrainingInfo
        val inflater =
            parent?.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.training_list_item, null)

        val difficultyTextView: TextView = view.findViewById(R.id.textDifficulty)
        val countTextView: TextView = view.findViewById(R.id.textCount)

        difficultyTextView.text = trainingInfo.difficulty
        countTextView.text = trainingInfo.count.toString()

        return view
    }
}
