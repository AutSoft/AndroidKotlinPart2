package hu.aut.security.demo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import hu.aut.security.demo.model.Database
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    companion object {
        val KEY_USERNAME = "KEY_USERNAME"
        val TEST_USERNAME = "Teszt"
        val TEST_PASSWORD = "tesztpassword"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        actionBar.title=getString(R.string.mainTitle)


        register_button!!.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }


        signin_button!!.setOnClickListener(View.OnClickListener {
            val username = name_editText!!.text.toString()

            val password = name_editText!!.text.toString()

            if (BuildConfig.isAdmin) {
                if (username == TEST_USERNAME && password == TEST_PASSWORD) {
                    logInUser(username)
                    return@OnClickListener
                }
            }


            Thread(Runnable {
                val database = Database(this@LoginActivity)
                val ok = database.authenticateUser(username, password)

                Log.d("SecCode", "Username: $username Password: $password")

                runOnUiThread {
                    if (ok) {
                        logInUser(username)
                    } else {
                        Toast.makeText(this@LoginActivity, "Bad username or password!", Toast.LENGTH_SHORT).show()
                    }
                }
            }).start()
        })
    }

    private fun logInUser(username: String) {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.putExtra(KEY_USERNAME, username)
        startActivity(intent)
    }


}
