package valentyn.androidtasks.models

import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import valentyn.androidtasks.R
import valentyn.androidtasks.views.BaseContract

class City(
    override val id: Int,
    override val name: String,
    override val url: String,
    override var about: String,
    override var country: String,
    override var site: String,
    override var select: Boolean
) : BaseContract.Model, Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    )

    override fun getTextSelected(): Int {
        return if (this.select)
            R.string.button_selected
        else
            R.string.button_unselected
    }

    override fun getColorSelected(): Int {
        return if (this.select)
            Color.GREEN
        else
            Color.GRAY
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(url)
        parcel.writeString(about)
        parcel.writeString(country)
        parcel.writeString(site)
        parcel.writeByte(if (select) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<City> {
        override fun createFromParcel(parcel: Parcel): City {
            return City(parcel)
        }

        override fun newArray(size: Int): Array<City?> {
            return arrayOfNulls(size)
        }
    }
}





