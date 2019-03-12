package valentyn.androidtasks.graphics

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.graphics.Bitmap

class FingerPaint : Activity() {

    private var dv: DrawingView? = null
    private var paint: Paint? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dv = DrawingView(this)
        setContentView(dv)
        paint = Paint()

        paint?.apply {
            isAntiAlias = true
            isDither = true
            color = Color.GREEN
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            strokeWidth = 12F
        }
    }

    inner class DrawingView(internal var context: Context) : View(context) {

        private var bitmap: Bitmap? = null
        private var canvas: Canvas? = null
        private val path: Path = Path()
        private val bitmapPaint: Paint = Paint(Paint.DITHER_FLAG)
        private val circlePaint: Paint = Paint()
        private val circlePath: Path = Path()

        private var mX: Float = 0.toFloat()
        private var mY: Float = 0.toFloat()

        init {
            circlePaint.isAntiAlias = true
            circlePaint.color = Color.BLUE
            circlePaint.style = Paint.Style.STROKE
            circlePaint.strokeJoin = Paint.Join.MITER
            circlePaint.strokeWidth = 4f
        }

        override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
            super.onSizeChanged(w, h, oldw, oldh)

            bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            canvas = Canvas(bitmap!!)
        }

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)

            canvas.drawBitmap(bitmap!!, 0F, 0F, bitmapPaint)
            canvas.drawPath(path, paint!!)
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
            canvas!!.drawPath(path, paint!!)
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
    }

    companion object {
        private const val TOUCH_TOLERANCE = 4f
    }
}