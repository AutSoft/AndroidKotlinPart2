package hu.aut.security.demo.model


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "obfuscatedemo"

        // Contacts table name
        const val TABLE_USERS = "users"

        // Contacts Table Columns names
        const val KEY_ID = "id"
        const val KEY_USERNAME = "username"
        const val KEY_PASSWORD = "password"
        const val KEY_WELCOME = "welcome"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USERNAME + " TEXT UNIQUE,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_WELCOME + " TEXT" + ")")
        db.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

}