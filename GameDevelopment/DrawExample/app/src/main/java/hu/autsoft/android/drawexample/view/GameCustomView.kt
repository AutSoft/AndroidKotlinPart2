package hu.autsoft.android.drawexample.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import java.lang.Thread.sleep

class GameCustomView: View {

    constructor(context: Context): super(context)
    constructor(context: Context,attrs: AttributeSet): super(context,attrs)
    constructor(context: Context,attrs: AttributeSet, style: Int): super(context,attrs,style)

    private val rect=RectF(100f,100f,400f,400f)
    private val paint=Paint()

    var isRunning=true

    override fun onDraw(canvas: Canvas) {
        paint.color=Color.RED

        var i=0
        while(isRunning) {
            i+=5
            canvas.drawArc(rect, 30.0f, 300.0f, true, paint)
            sleep(10)
        }
    }


}