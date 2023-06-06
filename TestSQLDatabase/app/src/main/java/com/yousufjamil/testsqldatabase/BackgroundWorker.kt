package com.yousufjamil.testsqldatabase

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.json.JSONObject
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder

class BackgroundWorker(val context: Context): AsyncTask<String, Void, String>() {

    lateinit var alertDialog: AlertDialog

    val mProgress = ProgressDialog(context)
    override fun onPreExecute() {
//        backgroundWorker(MainActivity())
//        alertDialog = AlertDialog.Builder(context).create()
//        alertDialog.setTitle("Message Recieved")

        super.onPreExecute()

        mProgress.setCancelable(false)
        mProgress.setMessage("Recieving data...")
        mProgress.show()
    }

    override fun doInBackground(vararg params: String?): String? {
        val idToFind = params[1]
        println(idToFind)
        val content = StringBuffer()
        try {
            val url = URL("https://ginastic.co/spare/45/index.php")
            val con = url.openConnection() as HttpURLConnection
            con.requestMethod = "POST"
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
            con.doOutput = true

            val outputStream = con.outputStream
//            val writer = BufferedWriter(OutputStreamWriter(outputStream, "UTF-8"))
//            writer.write("id=$idToFind")

//            val input = JSONObject()
//            input.put("id", "1")
//            outputStream.write(input.toString().toByteArray(charset("UTF-8")))
            val wr = OutputStreamWriter(outputStream)
            println(URLEncoder.encode("id", "UTF-8")+"="+URLEncoder.encode(idToFind, "UTF-8"))
            wr.write(URLEncoder.encode("id", "UTF-8")+"="+URLEncoder.encode(idToFind, "UTF-8"))
            wr.flush();
//            outputStream.flush()
//            outputStream.close()

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

//            println("Result pre-test 1")
//            val url = URL("https://ginastic.co/spare/45/index.php")
//            val httpURLConnection = url.openConnection() as HttpURLConnection
//            println("Result: ${httpURLConnection.responseCode}")
//            httpURLConnection.requestMethod = "POST"
//            httpURLConnection.doOutput = true
//            httpURLConnection.doInput = true
//            httpURLConnection.connect()
//            println("Result pre-test 2")
//
//            println("TestStatus: ${httpURLConnection.responseCode}")
//
//            val outputStream = httpURLConnection.outputStream
//            println("Result pre-test 2")
//            val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream, "UTF-8"))
//            println("Result pre-test 2")
//            val postData = URLEncoder.encode("id", "UTF-8")+"="+URLEncoder.encode(idToFind, "UTF-8")
//            println("Result pre-test 2")
//            bufferedWriter.write(postData)
//            bufferedWriter.flush()
//            bufferedWriter.close()
//            outputStream.close()
//            println("Result pre-test 3")
//
//            val inputStream = httpURLConnection.inputStream
//            val bufferedReader = BufferedReader(InputStreamReader(inputStream, "iso-8859-1"))
//            println("Result pre-test 4")
//            while ((bufferedReader.readLine().also { line = it }) != null) {
//                result += line
//                println("Result pre-test 5")
//            }
//            bufferedReader.close()
//            inputStream.close()
//            httpURLConnection.disconnect()
//            println("Result pre-test 6")
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

        val showRecieved = MaterialAlertDialogBuilder(context)
        if (result != "") {
            showRecieved.setMessage("Recieved: $result")
        } else {
            showRecieved.setMessage("None Found")
        }
        showRecieved.setPositiveButton("OK") {_, _ -> }
        showRecieved.show()
//        alertDialog.setMessage(result)
//        alertDialog.show()

        super.onPostExecute(result)
    }
}