package hu.aut.android.uilibsdemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import ir.mirrajabi.viewfilter.core.ViewFilter
import ir.mirrajabi.viewfilter.renderers.BlurRenderer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*ViewFilter.getInstance(this)
            //Use blur effect or implement your custom IRenderer
            .setRenderer(BlurRenderer(13))
            .applyFilterOnView(btnPhysics, rootView)*/

        btnPhysics.setOnClickListener {
            startActivity(Intent(this@MainActivity, PhysicsActivity::class.java))
        }

        btnVoronoi.setOnClickListener {
            startActivity(Intent(this@MainActivity, VoroniActivity::class.java))
        }

        btnGif.setOnClickListener {
            startActivity(Intent(this@MainActivity, GifActivity::class.java))
        }

        btnUIDemo.setOnClickListener {
            startActivity(Intent(
                this@MainActivity, LabelAlertCircleImageActivity::class.java))
        }
    }
}
