package valentyn.androidtasks.views

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_data.view.*

import valentyn.androidtasks.R
import valentyn.androidtasks.models.Data

class DatasAdapter(private val mDataset: List<Data>, context: Context) :
    RecyclerView.Adapter<DatasAdapter.DataViewHolder>() {

    val mContext = context

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): DataViewHolder {

        return DataViewHolder(LayoutInflater.from(mContext)
            .inflate(R.layout.item_data, parent, false))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        val data = mDataset.get(position)

        holder.nameTextView.text = data.name
        holder.descriptionTextView.text = data.about
        Glide
            .with(mContext)
            .load(data.photo)
            .into(holder.PhotoView)
    }

    class DataViewHolder(val view: View) : RecyclerView.ViewHolder(view){

        val nameTextView =  view.nameTextView
        val descriptionTextView =  view.descriptionTextView
        val PhotoView = view.PhotoView
    }

    override fun getItemCount() = mDataset.size
}