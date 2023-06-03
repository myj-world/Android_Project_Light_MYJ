package com.yousufjamil.testsqldatabase

import android.app.AlertDialog
import android.content.Context
import android.os.AsyncTask
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
        val idToFind = params[1]
        var result: String? = ""
        var line: String? = ""
        try {
            println("Result pre-test 1")
            val url = URL("https://ginastic.co/spare/45/index.php")
            val httpURLConnection = url.openConnection() as HttpURLConnection
            println("Result: ${httpURLConnection.responseCode}")
            httpURLConnection.requestMethod = "POST"
            httpURLConnection.doOutput = true
            httpURLConnection.doInput = true
            httpURLConnection.connect()
            println("Result pre-test 2")

            val outputStream = httpURLConnection.outputStream
            println("Result pre-test 2")
            val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream, "UTF-8"))
            println("Result pre-test 2")
            val postData = URLEncoder.encode("id", "UTF-8")+"="+URLEncoder.encode(idToFind, "UTF-8")
            println("Result pre-test 2")
            bufferedWriter.write(postData)
            bufferedWriter.flush()
            bufferedWriter.close()
            outputStream.close()
            println("Result pre-test 3")

            val inputStream = httpURLConnection.inputStream
            val bufferedReader = BufferedReader(InputStreamReader(inputStream, "iso-8859-1"))
            println("Result pre-test 4")
            while ((bufferedReader.readLine().also { line = it }) != null) {
                result += line
                println("Result pre-test 5")
            }
            bufferedReader.close()
            inputStream.close()
            httpURLConnection.disconnect()
            println("Result pre-test 6")
        } catch (e: IOException) {
            println("Error found in connecting to database: $e")
        } catch (e: MalformedURLException) {
            println("Error found in connecting to database: $e")
        }
        return result
    }

    override fun onPostExecute(result: String?) {
        println("Result is: $result")
//        alertDialog.setMessage(result)
//        alertDialog.show()

        super.onPostExecute(result)
    }
}