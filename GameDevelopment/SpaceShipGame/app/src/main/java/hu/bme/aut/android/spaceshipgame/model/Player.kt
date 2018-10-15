package hu.bme.aut.android.spaceshipgame.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

import hu.bme.aut.android.spaceshipgame.R

class Player(context: Context) : Ship() {

    companion object {
        const val SPRITE_HORIZONTAL = 1
        const val SPRITE_VERTICAL = 4
    }

    var elevation = 0f

    override val image: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ship)

    override fun step() {
        super.step()
        posX = 5
        posY -= (elevation / 10.0f * screenHeight.toFloat()).toInt()
        if (posY > screenHeight) {
            posY = screenHeight
        }
        if (posY < spriteHeight) {
            posY = spriteHeight
        }
    }

    override fun setSpriteSizes() {
        spriteWidth = image.width / SPRITE_HORIZONTAL
        spriteHeight = image.height / SPRITE_VERTICAL
    }

}
