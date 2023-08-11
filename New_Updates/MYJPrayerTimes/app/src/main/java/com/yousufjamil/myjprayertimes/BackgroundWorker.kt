package com.yousufjamil.myjprayertimes

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder

class BackgroundWorker(val context: Context, val city: String): AsyncTask<Void, Void, String>() {


    val mProgress = ProgressDialog(context)
    var finalresult: String? = ""
    override fun onPreExecute() {

        super.onPreExecute()

        mProgress.setCancelable(false)
        mProgress.setMessage("Loading...")
        mProgress.show()
    }

    override fun doInBackground(vararg params: Void?): String? {
        val content = StringBuffer()
        try {
            val url = URL(BASE_URL+city)
            val con = url.openConnection() as HttpURLConnection
            con.requestMethod = "GET"

            var status: Any? = con.responseCode
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
            println("Error found in connecting to database: $e")
        } catch (e: MalformedURLException) {
            println("Error found in connecting to database: $e")
        }
        return content.toString()
    }

    override fun onPostExecute(result: String?) {
        println("Result is: $result")

        mProgress.hide()

        if (result != "") {
            finalresult = result
        } else {
            finalresult = "Error"
        }

        super.onPostExecute(result)
    }
}