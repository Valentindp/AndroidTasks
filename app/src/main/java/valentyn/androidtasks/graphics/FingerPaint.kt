package valentyn.androidtasks.graphics

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.content.Intent
import android.graphics.*
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_finger_paint.*
import valentyn.androidtasks.R
import valentyn.androidtasks.utils.FileUtils
import java.io.FileOutputStream

class FingerPaint : AppCompatActivity(), GraphicsContract.View, ColorPickerDialog.OnColorChangedListener {

    private var dv: DrawingView? = null
    private var paint = Paint()
    private val presenter = FingerPaintPresenter()
    private val blur: MaskFilter = BlurMaskFilter(8f, BlurMaskFilter.Blur.NORMAL)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finger_paint)

        setSupportActionBar(toolbar_fp)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }

        presenter.onAttach(this)

        fab_button_drawing.setOnClickListener { presenter.saveDrawing() }
    }

    override fun setDrawingView() {
        paint.apply {
            isAntiAlias = true
            isDither = true
            color = Color.BLUE
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            strokeWidth = 12f
        }

        dv = DrawingView(this)
        lLDrawing.addView(dv)
    }

    override fun colorChanged(color: Int) {
        paint.color = color;
    }

    override fun onFinish() {
        finish()
    }

    override fun setResult() {
        val resultIntent = Intent()
        resultIntent.putExtra(FILE_DRAWING_URI, dv?.getBitmapUri().toString())
        setResult(Activity.RESULT_OK, resultIntent)
    }

    inner class DrawingView(internal var context: Context) : View(context) {

        private var bitmap: Bitmap? = null
        private var canvas: Canvas? = null
        private val path: Path = Path()
        private val bitmapPaint: Paint = Paint(Paint.DITHER_FLAG)
        private val circlePaint: Paint = Paint()
        private val circlePath: Path = Path()

        private var mX: Float = 0f
        private var mY: Float = 0f

        init {
            circlePaint.apply {
                isAntiAlias = true
                color = Color.BLACK
                style = Paint.Style.STROKE
                strokeJoin = Paint.Join.MITER
                strokeWidth = 4f
            }
        }

        override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
            super.onSizeChanged(w, h, oldw, oldh)

            bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            canvas = Canvas(bitmap!!)
        }

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)

            canvas.drawBitmap(bitmap!!, 0f, 0f, bitmapPaint)
            canvas.drawPath(path, paint)
            canvas.drawPath(circlePath, circlePaint)
        }

        private fun touchStart(x: Float, y: Float) {
            path.reset()
            path.moveTo(x, y)
            mX = x
            mY = y
        }

        private fun touchMove(x: Float, y: Float) {
            val dx = Math.abs(x - mX)
            val dy = Math.abs(y - mY)
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                path.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2)
                mX = x
                mY = y

                circlePath.reset()
                circlePath.addCircle(mX, mY, 30F, Path.Direction.CW)
            }
        }

        private fun touchUp() {
            path.lineTo(mX, mY)
            circlePath.reset()
            // commit the path to our offscreen
            canvas!!.drawPath(path, paint)
            // kill this so we don't double draw
            path.reset()
        }

        override fun onTouchEvent(event: MotionEvent): Boolean {
            val x = event.x
            val y = event.y

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    touchStart(x, y)
                    invalidate()
                }
                MotionEvent.ACTION_MOVE -> {
                    touchMove(x, y)
                    invalidate()
                }
                MotionEvent.ACTION_UP -> {
                    touchUp()
                    invalidate()
                }
            }
            return true
        }

        fun getBitmapUri(): Uri? {
            val file = FileUtils.getTempFile(context)
            val fileOS = FileOutputStream(file)

            bitmap?.compress(Bitmap.CompressFormat.JPEG, 85, fileOS)
            fileOS.close()

            return FileUtils.getUri(context, file)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_fp, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        paint.apply {
            xfermode = null
            alpha = 0xFF
        }

        when (item.itemId) {
            R.id.action_color -> {
                val coloDialog = ColorPickerDialog(this, this, paint.color)
                coloDialog.show()
                return true
            }
            R.id.action_blur -> {
                if (paint.maskFilter !== blur) {
                    paint.maskFilter = blur
                } else {
                    paint.maskFilter = null
                }
                return true
            }
            R.id.action_erase -> {
                paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
                return true
            }
            R.id.action_srcATop -> {
                paint.apply {
                    xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP)
                    alpha = 0x80
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val TOUCH_TOLERANCE = 4f
        const val FILE_DRAWING_URI = "DRAWING_URI"
    }
}