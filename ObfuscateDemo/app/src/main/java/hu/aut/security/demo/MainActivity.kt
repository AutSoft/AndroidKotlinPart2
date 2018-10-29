package hu.aut.security.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import hu.aut.security.demo.model.Database
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username = intent.getStringExtra(LoginActivity.KEY_USERNAME)

        if (username == LoginActivity.TEST_USERNAME) {
            welcomeText!!.text = "Teszt welcome"
            return
        }

        Thread(Runnable {
            val database = Database(this@MainActivity)
            val welcome = database.getWelcomeForUser(username)

            runOnUiThread { welcomeText!!.text = welcome }
        }).start()

    }
}
