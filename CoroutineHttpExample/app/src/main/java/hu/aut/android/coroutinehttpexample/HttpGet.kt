package hu.aut.android.coroutinehttpexample

import android.content.Context
import android.support.v4.content.LocalBroadcastManager
import android.content.Intent
import android.os.AsyncTask
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class HttpGet(private val ctx: Context) {

    fun doGet(address: String): String {
        var result = ""
        var connection: HttpURLConnection? = null
        var inputStream: InputStream? = null
        try {
            val url = URL(address)
            connection = url.openConnection() as HttpURLConnection
            connection.setConnectTimeout(10000)
            connection.setReadTimeout(10000)


            if (connection.getResponseCode() === HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream()

                var ch: Int
                val bos = ByteArrayOutputStream()
                ch = inputStream.read()
                while (ch != -1) {
                    bos.write(ch)
                    ch = inputStream.read()
                }

                result = String(bos.toByteArray())
            }

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }

            if (connection != null) {
                connection.disconnect()
            }
        }

        return result
    }
}
