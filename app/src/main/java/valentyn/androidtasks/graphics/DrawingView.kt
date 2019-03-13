package valentyn.androidtasks.graphics

import android.content.Context
import android.graphics.*
import android.net.Uri
import android.view.MotionEvent
import android.view.View
import valentyn.androidtasks.utils.FileUtils
import java.io.FileOutputStream

class DrawingView(internal var context: Context) : View(context) {

    private var paint = Paint()
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
        paint.apply {
            isAntiAlias = true
            isDither = true
            color = Color.BLUE
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            strokeWidth = 12f
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

    companion object {
        private const val TOUCH_TOLERANCE = 4f
    }
}