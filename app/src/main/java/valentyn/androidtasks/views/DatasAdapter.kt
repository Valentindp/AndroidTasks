package valentyn.androidtasks.views

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_data.view.*

import valentyn.androidtasks.R
import valentyn.androidtasks.models.Data

class DatasAdapter(private val mDataset: List<Data>) :
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
        private var data: Data? = null

        fun bindData(data: Data) {
            this.data = data

            Picasso.get()
                .load(data.url)
                .fit()
                .error(R.drawable.ic_error_black_24dp)
                .into(view.PhotoView)

            view.nameTextView.text = data.name
            view.descriptionTextView.text = data.about
            if (data.Select){
                view.selectedTextView.text =  "Selected"
                view.selectedTextView.setTextColor(Color.GREEN)
            } else
            {
                view.selectedTextView.text =  "Unselected"
                view.selectedTextView.setTextColor(Color.GRAY)
            }
        }
    }

    override fun getItemCount() = mDataset.size
}