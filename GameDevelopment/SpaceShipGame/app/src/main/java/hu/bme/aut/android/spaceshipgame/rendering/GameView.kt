package hu.bme.aut.android.spaceshipgame.rendering

import android.content.Context
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameView : SurfaceView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var renderLoop: RenderLoop? = null

    init {
        holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {

            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
                val loop = RenderLoop(context, this@GameView, width, height)
                loop.running = true
                loop.start()
                renderLoop = loop

            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                //Stop rendering

                renderLoop?.running=false
            }


        })
    }

    fun setPlayerElevation(elevation: Float){
        renderLoop?.setPlayerElevation(elevation)
    }


}
