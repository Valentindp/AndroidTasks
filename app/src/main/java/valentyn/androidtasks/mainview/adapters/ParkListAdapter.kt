package valentyn.androidtasks.mainview.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_park.view.*
import valentyn.androidtasks.R
import valentyn.androidtasks.BaseContract

class ParkListAdapter(datas: List<BaseContract.Data>, listener: DataItemListener) :
    RecyclerView.Adapter<ParkListAdapter.ParkViewHolder>() {

    private var dataset: List<BaseContract.Data> = datas
    private var itemListener: DataItemListener = listener

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
        holder.bindData(dataset[position], itemListener)
    }

    fun updateData(datas: List<BaseContract.Data>){
        dataset = datas
        notifyDataSetChanged()
    }

    class ParkViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(park: BaseContract.Data, itemListener: DataItemListener) {

            if (park.url.isNotEmpty()) {
                Picasso.get()
                    .load(park.url)
                    .fit()
                    .error(R.drawable.ic_error_black_24dp)
                    .into(view.photoView)
            }

            view.apply {
                nameTextView.text = park.title
                descriptionTextView.text = park.description
                selectedTextView.setText(park.getTextSelected())
                selectedTextView.setTextColor(park.getColorSelected())
                setOnClickListener { itemListener.onTaskClick(park) }
            }
        }
    }

    override fun getItemCount() = dataset.size
}