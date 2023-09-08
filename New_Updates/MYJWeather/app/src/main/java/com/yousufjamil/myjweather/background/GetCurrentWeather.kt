package com.yousufjamil.myjweather.background

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import com.yousufjamil.myjweather.BASE_URL
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

@Suppress("DEPRECATION")
class GetCurrentWeather(context: Context, private val city: String): AsyncTask<Void, Void, String>() {
    val mProgress = ProgressDialog(context)
    var finalresult: String? = ""
    override fun onPreExecute() {

        super.onPreExecute()

        mProgress.setCancelable(false)
        mProgress.setMessage("Retrieving Weather...")
        mProgress.show()
    }

    override fun doInBackground(vararg params: Void?): String? {
        val content = StringBuffer()
        try {
            val url = URL("$BASE_URL&q=$city")
            val con = url.openConnection() as HttpURLConnection
            con.requestMethod = "GET"

            val status: Any = con.responseCode
            println(status)

            val recieve = BufferedReader(InputStreamReader(con.inputStream))
            var inputLine: String?

            while (recieve.readLine().also { inputLine = it } != null) {
                content.append(inputLine)
            }
            recieve.close()

            con.disconnect()

            println("RecievedInput $content")
        } catch (e: IOException) {
            println("Error: $e")
        } catch (e: MalformedURLException) {
            println("Error: $e")
        }
        return content.toString()
    }

    override fun onPostExecute(result: String?) {
        println("Result is: $result")

        mProgress.hide()

        finalresult = if (result != "") result else "Error"

        super.onPostExecute(result)
    }
}