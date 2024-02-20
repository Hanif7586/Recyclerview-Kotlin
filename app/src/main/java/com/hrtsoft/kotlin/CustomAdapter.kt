import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.hrtsoft.kotlin.AnotherActivity
import com.hrtsoft.kotlin.ItemsViewModel
import com.hrtsoft.kotlin.R

class CustomAdapter(private val originalList: List<ItemsViewModel>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>(), Filterable {

    private var filteredList: List<ItemsViewModel> = originalList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = filteredList[position]
        holder.imageView.setImageResource(item.image)
        holder.textView.text = item.text
        holder.description.text = item.des

        holder.alldta.setOnClickListener {
            // Open another activity with the selected item data
            val intent = Intent(holder.itemView.context, AnotherActivity::class.java)
            intent.putExtra("image", item.image) // Pass any required data to the next activity
            intent.putExtra("text", item.text)
            intent.putExtra("description", item.des)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val queryString = constraint.toString().toLowerCase()
                filteredList = if (queryString.isEmpty()) {
                    originalList
                } else {
                    originalList.filter {
                        it.text.toLowerCase().contains(queryString) ||
                                it.des.toLowerCase().contains(queryString)
                    }
                }

                val results = FilterResults()
                results.values = filteredList
                return results
            }

            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as List<ItemsViewModel>
                notifyDataSetChanged()
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
        val description: TextView = itemView.findViewById(R.id.desID)
        val alldta:CardView=itemView.findViewById(R.id.alldataid)
    }
}
