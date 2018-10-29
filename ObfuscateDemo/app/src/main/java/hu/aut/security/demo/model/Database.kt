package hu.aut.security.demo.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class Database(context: Context) {

    private val databaseHandler: DatabaseHandler = DatabaseHandler(context)

    fun saveUser(user: User): Long {
        val db = databaseHandler.writableDatabase
        val values = ContentValues()
        values.put(DatabaseHandler.KEY_USERNAME, user.username)
        values.put(DatabaseHandler.KEY_PASSWORD, user.password)
        values.put(DatabaseHandler.KEY_WELCOME, user.welcomeMessage)

        val success = db.insert(DatabaseHandler.TABLE_USERS, null, values)
        db.close()
        return success
    }

    fun authenticateUser(username: String, password: String): Boolean {
        val db = databaseHandler.readableDatabase
        val cursor = db.query(DatabaseHandler.TABLE_USERS, arrayOf(DatabaseHandler.KEY_ID),
                DatabaseHandler.KEY_USERNAME + "='" + username + "' AND " + DatabaseHandler.KEY_PASSWORD + "='" + password + "'", null, null, null, null, null)
        val isLoggedIn = cursor != null && cursor.count > 0
        cursor?.close()
        return isLoggedIn
    }

    fun getWelcomeForUser(username: String): String {
        val db = databaseHandler.readableDatabase
        val cursor = db.query(DatabaseHandler.TABLE_USERS, arrayOf(DatabaseHandler.KEY_WELCOME),
                DatabaseHandler.KEY_USERNAME + "='" + username + "'", null, null, null, null, null)
        var welcome = ""

        if (cursor != null) {
            cursor.moveToFirst()
            welcome = cursor.getString(cursor.getColumnIndex(DatabaseHandler.KEY_WELCOME))
            cursor.close()
        }

        return welcome
    }


}
