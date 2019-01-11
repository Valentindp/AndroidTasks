package valentyn.androidtasks.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_park.view.*

import valentyn.androidtasks.R
import valentyn.androidtasks.models.Park

class ParksAdapter(private val mDataset: List<Park>, val clickListener: (Park) -> Unit) :
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

        holder.bindData(mDataset.get(position), clickListener)
    }

    class ParkViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private var Park: Park? = null

        fun bindData(Park: Park, clickListener: (Park) -> Unit) {
            this.Park = Park

            Picasso.get()
                .load(Park.url)
                .fit()
                .error(R.drawable.ic_error_black_24dp)
                .into(view.photoView)

            view.nameTextView.text = Park.name
            view.descriptionTextView.text = Park.about
            view.selectedTextView.setText(Park.getTextSelected())
            view.selectedTextView.setTextColor(Park.getColorSelected())
            view.setOnClickListener { clickListener(Park) }
        }

    }

    override fun getItemCount() = mDataset.size

}