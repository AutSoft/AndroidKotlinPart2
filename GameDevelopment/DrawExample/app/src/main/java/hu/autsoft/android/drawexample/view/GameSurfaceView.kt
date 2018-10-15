package hu.autsoft.android.drawexample.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameSurfaceView: SurfaceView {

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context,attrs)
    constructor(context: Context, attrs: AttributeSet, style: Int): super(context,attrs,style)

    private var isRunning: Boolean=true

    init {
        holder.addCallback(object : SurfaceHolder.Callback{
            override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
               startRender()
                isRunning=true
            }

            override fun surfaceDestroyed(holder: SurfaceHolder?) {
                isRunning=false
            }

            override fun surfaceCreated(holder: SurfaceHolder?) {

            }

        })
    }

    private val rect= RectF(100f,100f,400f,400f)
    private val paint= Paint()
    private val background = Paint()



    private fun startRender() {
        paint.color= Color.RED
        background.color=Color.WHITE
        background.style=Paint.Style.FILL

        Thread{
            var i=0.0f
            while(isRunning) {
                i+=5.0f

                var canvas: Canvas? = null

                try {
                    canvas = holder.lockCanvas()
                    canvas.drawRect(rect,background)
                    canvas.drawArc(rect, (30.0f+i)%360.0f, 300.0f, true, paint)
                    Thread.sleep(10)
                }finally {
                    canvas?.let { holder.unlockCanvasAndPost(canvas)  }

                }



            }

        }.start()


    }

}