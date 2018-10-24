package hu.aut.android.qrzxingdemo

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.zxing.Result
import com.livinglifetechway.quickpermissions.annotations.WithPermissions
import kotlinx.android.synthetic.main.activity_main.*
import me.dm7.barcodescanner.zxing.ZXingScannerView

class MainActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {
    public override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        setContentView(R.layout.activity_main)
    }

    public override fun onResume() {
        super.onResume()
        startCamera()
    }

    @WithPermissions(
        permissions = [Manifest.permission.CAMERA]
    )
    private fun startCamera() {
        zxingView.setResultHandler(this)
        zxingView.startCamera()
    }

    public override fun onPause() {
        super.onPause()
        zxingView.stopCamera()
    }

    override fun handleResult(rawResult: Result) {
        tvScan.text = rawResult.text
        zxingView.resumeCameraPreview(this)
    }
}
