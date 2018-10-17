package hu.aut.android.uilibsdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cn.pedant.SweetAlert.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_label_alert_circle_image.*

class LabelAlertCircleImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_label_alert_circle_image)

        btnSweetDialog.setOnClickListener {
            SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Biztos benne?")
                .setContentText("A rendelés nem visszavonható!")
                .setConfirmText("Igen, megrendelem!")
                .setConfirmClickListener{
                    it.dismissWithAnimation()
                }
                .setCancelButton("Mégsem") { sDialog -> sDialog.dismissWithAnimation() }
                .show()
        }
    }
}
