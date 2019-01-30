package valentyn.androidtasks.views

import android.support.v7.app.AppCompatActivity
import android.content.Intent
import valentyn.androidtasks.R
import android.widget.ProgressBar
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_splash_screen.*
import java.util.*

class SplashScreenActivity : AppCompatActivity() {
    private var timer: Timer? = null
    private var progressBar: ProgressBar? = null
    private var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        progressBar?.progress = 0

        val period: Long = 100
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                if (i < 100) {
                    runOnUiThread { textView.text = "$i%" }
                    progressBar?.progress = i
                    i++
                } else {
                    timer?.cancel()
                    val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }, 0, period)
    }

}