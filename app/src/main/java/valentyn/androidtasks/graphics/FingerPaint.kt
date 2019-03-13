package valentyn.androidtasks.graphics

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_finger_paint.*
import valentyn.androidtasks.R
import android.content.Intent

class FingerPaint : Activity(), GraphicsContract.View {

    private var dv: DrawingView? = null
    private val presenter = FingerPaintPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finger_paint)

        presenter.onAttach(this)
        fab_button_drawing.setOnClickListener { presenter.saveDrawing() }
    }

    override fun setDrawingView() {
        dv = DrawingView(this)
        rlDrawing.addView(dv)
    }

    override fun onFinish() {
        finish()
    }

    override fun setResult() {
        val resultIntent = Intent()
        resultIntent.putExtra(FILE_DRAWING_URI, dv?.getBitmapUri().toString())
        setResult(Activity.RESULT_OK, resultIntent)
    }

    companion object {
        const val FILE_DRAWING_URI = "DRAWING_URI"
    }
}