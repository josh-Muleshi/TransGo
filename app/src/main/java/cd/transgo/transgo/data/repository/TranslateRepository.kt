package cd.transgo.transgo.data.repository

import android.widget.Toast
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TranslateRepository @Inject constructor(private val firebaseTranslator: FirebaseTranslator, val context: ApplicationContext) {
    fun translate(source: String): String {
        var translatetxt = "Traduction..."
        val options = FirebaseTranslatorOptions.Builder()
            .setSourceLanguage(FirebaseTranslateLanguage.FR)
            .setTargetLanguage(FirebaseTranslateLanguage.SW)
            .build()
        val frenchSwahiliTranslator = FirebaseNaturalLanguage.getInstance().getTranslator(options)

        frenchSwahiliTranslator.downloadModelIfNeeded()
            .addOnSuccessListener {
                frenchSwahiliTranslator.translate(source)
                    .addOnSuccessListener { translatedText ->
                        translatetxt = translatedText
                    }
                    .addOnFailureListener { exception ->
                        //Toast.makeText(context, "echec de traduction: ${exception.message}", Toast.LENGTH_SHORT).show()
                    }
            }
            .addOnFailureListener { exception ->
                //Toast.makeText(context, "echec de traduction: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
        return translatetxt
    }
}