package valentyn.androidtasks.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_park.view.*

import valentyn.androidtasks.R
import valentyn.androidtasks.models.Park

class ParksAdapter(private val dataset: List<Park>, private val clickListener: (Long) -> Unit) :
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
        holder.bindData(dataset[position], clickListener)
    }

    class ParkViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(park: Park, clickListener: (Long) -> Unit) {

            Picasso.get()
                .load(park.url)
                .fit()
                .error(R.drawable.ic_error_black_24dp)
                .into(view.photoView)

            view.apply {
                nameTextView.text = park.name
                descriptionTextView.text = park.about
                selectedTextView.setText(park.getTextSelected())
                selectedTextView.setTextColor(park.getColorSelected())
                setOnClickListener { clickListener(park.id) }
            }
        }
    }

    override fun getItemCount() = dataset.size
}