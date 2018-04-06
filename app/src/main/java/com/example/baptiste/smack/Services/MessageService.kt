package com.example.baptiste.smack.Services

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.baptiste.smack.Controllers.App
import com.example.baptiste.smack.Model.Channel
import com.example.baptiste.smack.Utilities.URL_GET_CHANNELS
import org.json.JSONException

/**
 * Created by Baptiste on 05/04/2018.
 */
object MessageService {

    val channels = ArrayList<Channel>(  )

    fun getChannels(context: Context, complete: (Boolean) -> Unit){

        val channelsRequest = object  : JsonArrayRequest(Method.GET, URL_GET_CHANNELS, null, Response.Listener {response ->

            try {
                for (x in 0 until response.length()) {
                    val channel = response.getJSONObject(x)
                    val name = channel.getString("name")
                    val chanDesc = channel.getString("description")
                    val chanId = channel.getString("_id")

                    val newChannel = Channel(name, chanDesc, chanId)
                    this.channels.add(newChannel)
                }
                complete(true)
            }catch (e: JSONException){
                Log.d("JSON", "EXC: "+e.localizedMessage)
            }

        }, Response.ErrorListener {error ->
            Log.d("ERROR","Could not retrieve channel.")
            complete(false)

        }){
            override fun getBodyContentType(): String {
                    return "application/json; charset=utf-8"
                }


            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String,String>()
                headers.put("Authorization", "Bearer ${App.prefs.authToken}")
                return headers

            }
        }

        App.prefs.requestQueue.add(channelsRequest)
    }


}