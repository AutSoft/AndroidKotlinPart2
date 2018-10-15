package hu.bme.aut.android.spaceshipgame.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import hu.bme.aut.android.spaceshipgame.R
import java.util.Random

class Enemy(context: Context) : Ship() {

    companion object {
        const val SPRITE_HORIZONTAL = 1
        const val SPRITE_VERTICAL = 6
        const val SPEED = 14
    }

    override val image: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.enemy)

    private val randomFloat: Float

    init {
        posX = screenWidth
        posY = screenHeight
        randomFloat = Random().nextFloat()
    }

    override fun step() {
        super.step()

        posX -= SPEED
        posY = (screenHeight * randomFloat).toInt()
    }

    override fun setSize(x: Int, y: Int) {
        super.setSize(x, y)
        posX = x
    }

    override fun setSpriteSizes() {
        spriteWidth = image.width / SPRITE_HORIZONTAL
        spriteHeight = image.height / SPRITE_VERTICAL
    }

}
