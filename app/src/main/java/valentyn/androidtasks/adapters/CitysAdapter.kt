package valentyn.androidtasks.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_city.view.*

import valentyn.androidtasks.R
import valentyn.androidtasks.models.City

class CitysAdapter(private val dataset: List<City>, private val clickListener: (City) -> Unit):
    RecyclerView.Adapter<CitysAdapter.CityViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CityViewHolder {
        return CityViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_city, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bindData(dataset[position], clickListener)
    }

    class CityViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(city: City, clickListener: (City) -> Unit) {

            Picasso.get()
                .load(city.url)
                .fit()
                .error(R.drawable.ic_error_black_24dp)
                .into(view.photoView)

            view.apply {
                nameTextView.text = city.name
                descriptionTextView.text = city.about
                selectedTextView.setText(city.getTextSelected())
                selectedTextView.setTextColor(city.getColorSelected())
                setOnClickListener { clickListener(city) }
            }
        }
    }

    override fun getItemCount() = dataset.size

}