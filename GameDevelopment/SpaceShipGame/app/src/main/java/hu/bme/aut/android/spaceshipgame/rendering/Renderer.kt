package hu.bme.aut.android.spaceshipgame.rendering

import android.content.Context
import android.graphics.Canvas
import hu.bme.aut.android.spaceshipgame.model.Background
import hu.bme.aut.android.spaceshipgame.model.Enemy
import hu.bme.aut.android.spaceshipgame.model.Player
import hu.bme.aut.android.spaceshipgame.model.Renderable
import java.util.*

class Renderer(private val context: Context, private val width: Int, private val height: Int) {

    private val random = Random()
    private val entitiesToDraw = mutableListOf<Renderable>()

    private val background = Background(context)
    private val player = Player(context)

    init {
        background.setSize(width, height)
        player.setSize(width, height)

        val enemy = Enemy(context)
        enemy.setSize(width, height)

        entitiesToDraw.add(enemy)
        entitiesToDraw.add(player)
    }

    fun step() {
        if (random.nextFloat() > 0.97) {
            val enemy = Enemy(context)
            enemy.setSize(width, height)
            entitiesToDraw.add(enemy)
        }

        entitiesToDraw.forEach(Renderable::step)
    }

    fun draw(canvas: Canvas) {
        background.render(canvas)
        entitiesToDraw.forEach { it.render(canvas) }
    }

    fun setPlayerElevation(elevation: Float) {
        player.elevation = elevation
    }

}