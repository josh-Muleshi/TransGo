package cd.transgo.transgo.data.repository

import android.content.Context
import cd.transgo.transgo.data.model.Translate
import cd.transgo.transgo.data.utils.ApiConstants.JSON_FILE
import cd.transgo.transgo.data.utils.getJSONFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedWriter
import java.io.FileWriter


class TranslateRepository (context: Context) {
    private val file = getJSONFile(context, JSON_FILE)

    fun getTranslate(source: String) = flow {

        val arrays = JSONArray(file);

        for (i: Int in 0 until arrays.length()){
            val currObject : JSONObject = arrays.getJSONObject(i)
            val name = currObject.getString("word");

            if(name == source) {

                val searchObject = Translate(
                    word = source,
                    translate = currObject.getString("translate"),
                    date = currObject.getString("date"),
                    id = currObject.getInt("id"),
                    state = currObject.getString("state")
                )
                emit(searchObject)
            }
        }
    }

    fun getForTranslator() = flow {

        val arrays = JSONArray(file);

        for (i: Int in 0 until arrays.length()){
            val currObject : JSONObject = arrays.getJSONObject(i)
            val name = currObject.getString("word")

            if(name.isEmpty()) {
                val searchObject = Translate(
                    word = currObject.getString("word"),
                    translate = currObject.getString("translate"),
                    date = currObject.getString("date"),
                    id = currObject.getInt("id"),
                    state = currObject.getString("state")
                )
                emit(searchObject)
            }
        }
    }

    suspend fun setTranslate(word: String){

        val arrays = JSONArray(file)

        for (i: Int in 0 until arrays.length()){
            val currObject : JSONObject = arrays.getJSONObject(i)
            val name = currObject.getString("word")

            if(name.isEmpty()) {
                BufferedWriter(withContext(Dispatchers.IO) {
                    FileWriter(file)
                }).apply {
                    write(currObject.put("word", word).toString())
                    close()
                }
            }
        }
    }
}