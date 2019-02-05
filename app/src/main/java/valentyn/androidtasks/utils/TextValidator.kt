package valentyn.androidtasks.utils

import android.text.Editable
import android.widget.TextView
import android.text.TextWatcher

abstract class TextValidator(private val textView: TextView) : TextWatcher {

    abstract fun validate(textView: TextView, text: String)

    override fun afterTextChanged(s: Editable) {

    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        val text = textView.text.toString()
        validate(textView, text)
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
    }

}