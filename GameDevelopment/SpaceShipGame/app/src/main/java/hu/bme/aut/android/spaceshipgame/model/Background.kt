package hu.bme.aut.android.spaceshipgame.model

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import hu.bme.aut.android.spaceshipgame.R

class Background(private val context: Context) : Renderable {

    private var width: Int = 0
    private var height: Int = 0
    private lateinit var bitmapDrawable: BitmapDrawable

    override fun step() {
        //Do nothing
    }

    override fun setSize(x: Int, y: Int) {
        this.width = x
        this.height = y

        val image = BitmapFactory.decodeResource(context.resources, R.drawable.background)

        bitmapDrawable = BitmapDrawable(context.resources, image)
        bitmapDrawable.tileModeX = Shader.TileMode.MIRROR
        bitmapDrawable.tileModeY = Shader.TileMode.MIRROR
        bitmapDrawable.setBounds(0, 0, width / 4, height / 4)
    }

    override fun render(canvas: Canvas) {
        val src = Rect(0, 0, (width / 4.0f).toInt(), (height / 4.0f).toInt())
        val dest = Rect(0, 0, width, height)
        canvas.drawBitmap(bitmapDrawable.bitmap, src, dest, null)
    }
}
