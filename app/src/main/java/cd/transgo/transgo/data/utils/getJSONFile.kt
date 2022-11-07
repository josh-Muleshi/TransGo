package cd.transgo.transgo.data.utils

import android.content.Context

fun getJSONFile(context: Context, file: String): String =
    context.assets.open(file).bufferedReader().use { it.readText() }
