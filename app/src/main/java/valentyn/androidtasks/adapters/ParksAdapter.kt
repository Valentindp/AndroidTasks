package valentyn.androidtasks.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_park.view.*

import valentyn.androidtasks.R
import valentyn.androidtasks.models.Park

class ParksAdapter(private val dataset: List<Park>, val clickListener: (Park) -> Unit) :
    RecyclerView.Adapter<ParksAdapter.ParkViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ParkViewHolder {
        return ParkViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_park, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ParkViewHolder, position: Int) {
        holder.bindData(dataset.get(position), clickListener)
    }

    class ParkViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(park: Park, clickListener: (Park) -> Unit) {
            Picasso.get()
                .load(park.url)
                .fit()
                .error(R.drawable.ic_error_black_24dp)
                .into(view.photoView)

            view.nameTextView.text = park.name
            view.descriptionTextView.text = park.about
            view.selectedTextView.setText(park.getTextSelected())
            view.selectedTextView.setTextColor(park.getColorSelected())
            view.setOnClickListener { clickListener(park) }
        }
    }

    override fun getItemCount() = dataset.size

}