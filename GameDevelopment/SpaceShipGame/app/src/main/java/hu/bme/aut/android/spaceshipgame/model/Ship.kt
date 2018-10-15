package hu.bme.aut.android.spaceshipgame.model

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect

abstract class Ship : Renderable {

    protected var spriteHeight: Int = 0
    protected var spriteWidth: Int = 0
    protected var screenWidth = 0
    protected var screenHeight = 0

    protected abstract val image: Bitmap

    protected var posX: Int = 0
    protected var posY: Int = 0
    private var state = 0

    init {
        posX = screenWidth
        posY = screenHeight
    }

    override fun step() {
        state++
    }

    override fun setSize(x: Int, y: Int) {
        this.screenWidth = x
        this.screenHeight = y
    }

    override fun render(canvas: Canvas) {
        setSpriteSizes()

        val spriteState=state % 4

        val x = 0
        val y = spriteState*spriteHeight

        val src = Rect(x, y, x + spriteWidth, y + spriteHeight)
        val dst = Rect(posX, posY, posX + spriteWidth * 4, posY + spriteHeight * 4)


        canvas.drawBitmap(image, src, dst, null)
    }

    protected abstract fun setSpriteSizes()

}
