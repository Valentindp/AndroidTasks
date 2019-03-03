package valentyn.androidtasks.mainview.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_city.view.*
import valentyn.androidtasks.R
import valentyn.androidtasks.BaseContract

class CityListAdapter(datas: List<BaseContract.Data>, listener: DataItemListener) :
    RecyclerView.Adapter<CityListAdapter.CityViewHolder>() {

    private var dataset: List<BaseContract.Data> = datas
    private var itemListener: DataItemListener = listener

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
        holder.bindData(dataset[position], itemListener)
    }

    fun updateData(datas: List<BaseContract.Data>) {
        dataset = datas
        notifyDataSetChanged()
    }

    class CityViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(city: BaseContract.Data, itemListener: DataItemListener) {

            if (city.url.isNotEmpty()) {
                Picasso.get()
                    .load(city.url)
                    .fit()
                    .error(R.drawable.ic_error_black_24dp)
                    .into(view.photoView)
            }

            view.apply {
                nameTextView.text = city.name
                descriptionTextView.text = city.description
                selectedTextView.setText(city.getTextSelected())
                selectedTextView.setTextColor(city.getColorSelected())
                setOnClickListener { itemListener.onTaskClick(city) }
            }
        }
    }

    override fun getItemCount() = dataset.size

}