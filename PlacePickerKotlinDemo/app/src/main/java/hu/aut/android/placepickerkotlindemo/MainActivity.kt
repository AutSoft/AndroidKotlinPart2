package hu.aut.android.placepickerkotlindemo

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.places.Places
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.android.gms.location.places.Place
import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException


class MainActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {

    private lateinit var googleApiClient: GoogleApiClient
    private val PLACE_PICKER_REQUEST = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        googleApiClient = GoogleApiClient.Builder(this)
            .addApi(Places.GEO_DATA_API)
            .addApi(Places.PLACE_DETECTION_API)
            .enableAutoManage(this, this)
            .build()

        btnPick.setOnClickListener {
            val builder = PlacePicker.IntentBuilder()
            try {
                startActivityForResult(builder.build(this@MainActivity),
                    PLACE_PICKER_REQUEST)
            } catch (e: GooglePlayServicesRepairableException) {
                e.printStackTrace()
            } catch (e: GooglePlayServicesNotAvailableException) {
                e.printStackTrace()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        googleApiClient.connect()
    }

    override fun onStop() {
        super.onStop()
        googleApiClient.disconnect()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PLACE_PICKER_REQUEST && resultCode == Activity.RESULT_OK) {
            val place = PlacePicker.getPlace(this, data)

            val stBuilder = StringBuilder()
            val placename = String.format("%s", place.name)
            val latitude = place.latLng.latitude.toString()
            val longitude = place.latLng.longitude.toString()
            val address = String.format("%s", place.address)
            stBuilder.append("Name: ")
            stBuilder.append(placename)
            stBuilder.append("\n")
            stBuilder.append("Latitude: ")
            stBuilder.append(latitude)
            stBuilder.append("\n")
            stBuilder.append("Logitude: ")
            stBuilder.append(longitude)
            stBuilder.append("\n")
            stBuilder.append("Address: ")
            stBuilder.append(address)
            tvPlace.text = stBuilder.toString()
        }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        Toast.makeText(this, "Google connect failed: ${p0.errorMessage}",
            Toast.LENGTH_LONG).show()
    }

}
