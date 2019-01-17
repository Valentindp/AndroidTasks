package valentyn.androidtasks.models

import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import valentyn.androidtasks.R
import java.io.Serializable

class Park(
    val id: Int,
    val name: String,
    val url: String,
    var about: String,
    var country: String,
    var site: String,
    var select: Boolean
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    )

    fun getTextSelected(): Int {
        return if (this.select)
            R.string.button_selected
        else
            R.string.button_unselected
    }

    fun getColorSelected(): Int {
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

    companion object CREATOR : Parcelable.Creator<Park> {
        override fun createFromParcel(parcel: Parcel): Park {
            return Park(parcel)
        }

        override fun newArray(size: Int): Array<Park?> {
            return arrayOfNulls(size)
        }
    }
}





