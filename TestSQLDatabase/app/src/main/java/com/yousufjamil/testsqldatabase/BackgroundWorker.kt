package com.yousufjamil.testsqldatabase

import android.app.AlertDialog
import android.content.Context
import android.os.AsyncTask
import android.widget.Toast
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder

class BackgroundWorker: AsyncTask<String, Void, String>() {

    lateinit var alertDialog: AlertDialog
    lateinit var context: Context

    fun backgroundWorker(ctx: Context) {
        context = ctx
    }

    override fun onPreExecute() {
//        backgroundWorker(MainActivity())
//        alertDialog = AlertDialog.Builder(context).create()
//        alertDialog.setTitle("Message Recieved")

        super.onPreExecute()
    }

    override fun doInBackground(vararg params: String?): String? {
        val type = params[0]
        val idToFind = params[1]
        try {
            val url = URL("https://myj.rf.gd/DatabaseForm/TestAndroidSQL/")
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.requestMethod = "POST"
            httpURLConnection.doOutput = true
            httpURLConnection.doInput = true

            val outputStream = httpURLConnection.outputStream
            val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream, "UTF-8"))
            val post_data = URLEncoder.encode("id", "UTF-8")+"="+URLEncoder.encode(idToFind, "UTF-8")
            bufferedWriter.write(post_data)
            bufferedWriter.flush()
            bufferedWriter.close()
            outputStream.close()

            val inputStream = httpURLConnection.inputStream
            val bufferedReader = BufferedReader(InputStreamReader(inputStream, "iso-8859-1"))
            var result: String? = ""
            var line: String? = ""
            while ((bufferedReader.readLine().also { line = it }) != null) {
                result += line
            }
            bufferedReader.close()
            inputStream.close()
            httpURLConnection.disconnect()
        } catch (e: IOException) {
            println("Error found in connecting to database: $e")
        } catch (e: MalformedURLException) {
            println("Error found in connecting to database: $e")
        }
        return null
    }

    override fun onPostExecute(result: String?) {
        println("Result is: $result")
//        alertDialog.setMessage(result)
//        alertDialog.show()

        super.onPostExecute(result)
    }
}