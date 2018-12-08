package valentyn.androidtasks.views

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivities
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_data.view.*

import valentyn.androidtasks.R
import valentyn.androidtasks.models.City
import android.content.Intent



class DatasAdapter(private val mDataset: List<City>) :
    RecyclerView.Adapter<DatasAdapter.DataViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): DataViewHolder {

        return DataViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_data, parent, false))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        val data = mDataset.get(position)
        holder.bindData(data)
    }

    class DataViewHolder(val view: View) : RecyclerView.ViewHolder(view){

        private var v: View = view
        private var city: City? = null

        fun bindData(city: City) {
            this.city = city

            Picasso.get()
                .load(city.url)
                .fit()
                .error(R.drawable.ic_error_black_24dp)
                .into(view.PhotoView)

            view.nameTextView.text = city.name
            view.descriptionTextView.text = city.about
            if (city.Select){
                view.selectedTextView.setText(R.string.button_selected_name1)
                view.selectedTextView.setTextColor(Color.GREEN)
            } else
            {
                view.selectedTextView.setText(R.string.button_selected_name2)
                view.selectedTextView.setTextColor(Color.GRAY)
            }
        }

        fun getCity(): City {return this.city!! }
    }

    override fun getItemCount() = mDataset.size
}