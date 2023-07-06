package com.rv198510.englishinsound

import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

object JsonHelper {

    val KEY_IMAGE_ID = "imageId"
    val KEY_NAME = "name"
    val KEY_SOUND_ID = "soundId"

    fun getItemsFromJson(fileName: String, context: Context): ArrayList<EnglishData> {

        val englishData = ArrayList<EnglishData>()

        try {
            val jsonString = loadJsonFromFile(fileName, context)
            val json = JSONObject(jsonString)
            val jsonItems = json.getJSONArray("English")


            for (index in 0 until jsonItems.length()) {
                val imageId = jsonItems.getJSONObject(index).getString(KEY_IMAGE_ID)
                val name = jsonItems.getJSONObject(index).getString(KEY_NAME)
                val soundId = jsonItems.getJSONObject(index).getString(KEY_SOUND_ID)
                englishData.add(EnglishData(imageId,name,soundId))
            }
        } catch (e: JSONException) {
            return englishData
        }

        return englishData
    }

    private fun loadJsonFromFile(filename: String, context: Context): String {
        var json = ""

        try {
            val input = context.assets.open(filename)
            val size = input.available()
            val buffer = ByteArray(size)
            input.read(buffer)
            input.close()
            json = buffer.toString(Charsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return json
    }
}