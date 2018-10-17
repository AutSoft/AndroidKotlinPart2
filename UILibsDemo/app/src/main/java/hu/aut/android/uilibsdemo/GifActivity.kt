package hu.aut.android.uilibsdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_gif.*
import org.apache.commons.io.IOUtils

class GifActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gif)

        gifImageView.setOnAnimationStop {
            runOnUiThread {
                Toast.makeText(
                    this@GifActivity, "Animation stopped",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnStart.setOnClickListener {
            gifImageView.setBytes(IOUtils.toByteArray(
                resources.openRawResource(R.raw.developer)))
            gifImageView.startAnimation()
        }

        btnStop.setOnClickListener {
            gifImageView.stopAnimation()
        }
    }
}

