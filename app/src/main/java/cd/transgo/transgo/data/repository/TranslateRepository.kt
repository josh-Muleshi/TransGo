package cd.transgo.transgo.data.repository

import android.content.Context
import cd.transgo.transgo.data.model.Translate
import cd.transgo.transgo.data.utils.ApiConstants.JSON_FILE
import cd.transgo.transgo.data.utils.getJSONFile
import kotlinx.coroutines.flow.flow
import org.json.JSONArray
import org.json.JSONObject

class TranslateRepository (context: Context) {
    private val file = getJSONFile(context, JSON_FILE)

    fun getTranslate(source: String) = flow {
        var name: String
        var searchObject: Translate

        val arrays = JSONArray(file);

        for (i: Int in 0 until arrays.length()){
            val currObject : JSONObject = arrays.getJSONObject(i)
            name = currObject.getString("word");

            if(name == source) {

                searchObject = Translate(
                    word = source,
                    translate = currObject.getString("translate"),
                    date = currObject.getString("date"),
                    id = currObject.getInt("id")
                )
                emit(searchObject)
            }
        }
    }
}