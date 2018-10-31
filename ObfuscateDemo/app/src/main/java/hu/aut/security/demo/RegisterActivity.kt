package hu.aut.security.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

import hu.aut.security.demo.model.Database
import hu.aut.security.demo.model.User
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_button!!.setOnClickListener {
            val username = name_editText!!.text.toString()

            val password = passwd_editText!!.text.toString()

            val welcome = welcome_editText!!.text.toString()

            Thread(Runnable {
                val database = Database(this@RegisterActivity)
                val user = User()
                user.password = password
                user.username = username
                user.welcomeMessage = welcome
                var success: Long = -1
                try {
                    success = database.saveUser(user)
                } catch (e: Exception) {

                }

                val successForOtherThread = success

                runOnUiThread {
                    if (successForOtherThread > 0) {
                        Toast.makeText(this@RegisterActivity, "User registered!", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@RegisterActivity, "User already registered!", Toast.LENGTH_SHORT).show()

                    }
                }
            }).start()
        }

    }
}
