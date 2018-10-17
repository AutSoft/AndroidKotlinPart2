package hu.aut.android.uilibsdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_voroni.*

class VoroniActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voroni)

        addFruit(R.drawable.ananas)
        addFruit(R.drawable.apple)
        addFruit(R.drawable.banana)
        addFruit(R.drawable.cherry)
        addFruit(R.drawable.coconut)
        addFruit(R.drawable.lemon)
        addFruit(R.drawable.melone)
        addFruit(R.drawable.orange)

    }

    private fun addFruit(fruitDrawableId: Int) {
        val imageView = ImageView(this)
        imageView.setImageResource(fruitDrawableId);
        voronoiView.addView(imageView)
    }
}
