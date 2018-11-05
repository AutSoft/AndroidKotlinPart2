package hu.aut.android.coroutinehttpexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.json.JSONObject
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    private val URL_BASE = "https://api.exchangeratesapi.io/latest?base=EUR"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // === DEMO 1
        /*var job = GlobalScope.launch {
            delay(1000L)
            Log.d("TAG_CO", "Hello from coroutine!")
        }

        //job.cancel()

        Log.d("TAG_CO", "Hello not from coroutine!")
        Thread.sleep(2000L)*/
        // === DEMO 1

        // === DEMO 2
        /*val executionTime = measureTimeMillis {
            runBlocking { println("sum is: ${runSuspendingFunctionsParalell()}") }
        }
        Log.d("TAG_CO", "Execution time: $executionTime")*/
        // === DEMO 2

        // === DEMO 3
        /*try {
            runBlocking { failedChildSuspendingFunctions() }
        } catch (ex: Exception) {
            Log.d("TAG_CO", "Exception thrown in parent. msg: " + ex.message)
        }*/
        // === DEMO 3

        // === DEMO 4
        btnGetRate.setOnClickListener {
            /*GlobalScope.launch(context = Dispatchers.Main) {
                var job = async(context = Dispatchers.Default) {
                    HttpGet(applicationContext).doGet(URL_BASE)
                } //returned Job is cancellable
                val jsonResponse = job.await()
                val hufValue = JSONObject(jsonResponse).getJSONObject("rates").getString("HUF")
                tvResult.text = hufValue
            }*/

            GlobalScope.launch(context = Dispatchers.Main) {
                var jsonResponse = withContext(context = Dispatchers.Default) {
                    HttpGet(applicationContext).doGet(URL_BASE)
                }
                val hufValue = JSONObject(jsonResponse).getJSONObject("rates").getString("HUF")
                tvResult.text = hufValue
            }
        }
        // === DEMO 4
    }

    suspend fun calcOne(): Int {
        delay(1000L)
        return 1
    }

    suspend fun calcTwo(): Int {
        delay(1000L)
        return 2
    }

    suspend fun runSuspendingFunctions(): Int {
        val one = calcOne()
        var two = calcTwo()
        return one + two
    }


    suspend fun runSuspendingFunctionsParalell(): Int {
        val one = GlobalScope.async { calcOne() }
        var two = GlobalScope.async { calcTwo() }
        return one.await() + two.await()
    }

    suspend fun failedChildSuspendingFunctions(): Int {
        val one: Deferred<Int> = GlobalScope.async<Int> {
            try {
                delay(4000L)
                1
            } finally {
                println("First child cancelled...")
            }
        }
        val two: Deferred<Int> = GlobalScope.async<Int> {
            throw Exception("Exception from second child")
        }

        return one.await() + two.await()
    }


}
