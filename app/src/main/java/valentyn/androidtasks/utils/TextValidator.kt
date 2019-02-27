package valentyn.androidtasks.utils

import android.content.res.Resources
import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher

abstract class TextValidator(private val textView: TextInputLayout) : TextWatcher {

    abstract fun validate(textView: TextInputLayout, s: Editable)

    override fun afterTextChanged(s: Editable) {
        validate(textView, s)
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
    }

}