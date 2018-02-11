package io.androidedu.weatherwidget

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import io.androidedu.weatherwidget.core.network.VolleyService
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity(), Response.Listener<JSONObject>, Response.ErrorListener {

    /**
     * Application Programming Interface
     * http://myjson.com//
     * https://www.w3schools.com/
     * http://json.parser.online.fr/
     * JSON (JavaScript Object Notation).
     * https://github.com/ihsanbal/LoggingInterceptor
     *
     * Synchronously and ASynchronously request?
     *
     * GET method'u kullanilirsa parametreleri url icerisinde gonderiyoruz. Basit yapıda parametre gonderilirken kullanilir. Link uzerinde gorulur.
     *
     * POST method'u kullanilirsa parametreleri getParams() icerisinde veriyoruz. Karmasik parametre gonderilirken kullanilir. Link uzerinde gorulmez.
     * Bilgi form'lar uzerinden gider. "Kayıt Formu" ekranlari gibi.
     *
     * PUT method'u kullanilirsa parametreleri yine getParams() icerisinde veriyoruz. Genelde server'a dosya gondermek icin kullanilir.
     * Gonderilen dosya server'da varsa yenisi ile degistirilir yoksa yenisi yaratilir.
     *
     * LifeCycleProblem?
     **/

    private val txtData by lazy { findViewById<TextView>(R.id.activity_main_txtData) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jsonObjectRequest()

//        jsonArrayRequest()

//        jsonStringRequest()
    }

    private fun jsonObjectRequest() {

        val url = "https://api.myjson.com/bins/x8yrj"

        val jsonObjReq = JsonObjectRequest(Request.Method.GET, url, null, this, this)

        jsonObjReq.tag = MainActivity::class.java

        VolleyService.build(this).requestQueue.add(jsonObjReq)
        VolleyService.build(this).requestQueue.start()
    }
//

//    private fun jsonArrayRequest() {
//
//        val url = "https://api.myjson.com/bins/16pyzz"
//
//        val jsonObjReq = JsonArrayRequest(Request.Method.GET, url, null, this, this)
//
//        jsonObjReq.tag = MainActivity::class.java
//
//        VolleyService.build(this).requestQueue.add(jsonObjReq)
//        VolleyService.build(this).requestQueue.start()
//    }

//    private fun jsonStringRequest() {
//
//        //jsonObjectRequestURL
//        val url1 = "https://api.myjson.com/bins/x8yrj"
//
//        //jsonArrayRequestURL
//        val url2 = "https://api.myjson.com/bins/16pyzz"
//
//        val jsonObjReq = StringRequest(Request.Method.GET, url1, this, this)
//
//        jsonObjReq.tag = MainActivity::class.java
//
//        VolleyService.build(this).requestQueue.add(jsonObjReq)
//        VolleyService.build(this).requestQueue.start()
//    }

    private fun getDataFromJson(response: JSONObject?) {

        txtData.text = response?.getString("name")

    }

    private fun getDataFromJson(response: JSONArray?) {

        txtData.text = response?.getJSONObject(0)?.getString("name")
    }

    private fun getDataFromJson(response: String?) {

        try {
            val responseJsonArray = JSONArray(response)
            getDataFromJson(responseJsonArray)

        } catch (e: JSONException) {

            val responseJsonObject = JSONObject(response)
            getDataFromJson(responseJsonObject)
        }
    }

    override fun onResponse(response: JSONObject?) {

        Log.e("MainActivity", response.toString())
        getDataFromJson(response)
    }

    override fun onErrorResponse(error: VolleyError?) {

    }
}
