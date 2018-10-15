package hu.bme.aut.android.spaceshipgame.rendering

import android.content.Context
import android.graphics.Canvas

class RenderLoop(context: Context, private val view: GameView, width: Int, height: Int) : Thread() {

    private val renderer = Renderer(context, width, height)

    var running = false

    override fun run() {
        while (running) {
            val startRender=getTime()
            draw()
            val endRender=getTime()

            val sleepTime= TIME_BETWEEN_FRAMES-(endRender-startRender)

            if(sleepTime>4){
                sleepThread(sleepTime)
            }else{
                sleepThread(5)
            }


        }
    }

    private fun draw() {
        renderer.step()

        var canvas: Canvas? = null

        try {
            canvas = view.holder.lockCanvas()
            synchronized(view.holder) {
                renderer.draw(canvas)
            }
        } finally {
            if (canvas != null) {
                view.holder.unlockCanvasAndPost(canvas)
            }
        }
    }

    fun setPlayerElevation(elevation: Float) {
        renderer.setPlayerElevation(elevation)
    }

    companion object {
        private const val FPS: Long = 30
        private const val TIME_BETWEEN_FRAMES = 1000 / FPS
    }

    private fun sleepThread(time: Long) {
        try {
            Thread.sleep(time)
        } catch (e: InterruptedException) {
            // ignored
        }
    }

    private fun getTime() = System.currentTimeMillis()

}