package hu.bme.aut.android.spaceshipgame.model

import android.graphics.Canvas

interface Renderable {
    fun step()
    fun setSize(x: Int, y: Int)
    fun render(canvas: Canvas)
}
