package cd.transgo.transgo.data.repository

import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions
import javax.inject.Inject

class TranslateRepository @Inject constructor(private val firebaseNaturalLanguage: FirebaseNaturalLanguage) {
    fun translate(source: String): String {
        var translatetxt = "Traduction..."
        val options = FirebaseTranslatorOptions.Builder()
            .setSourceLanguage(FirebaseTranslateLanguage.FR)
            .setTargetLanguage(FirebaseTranslateLanguage.SW)
            .build()
        val firebaseTranslator = FirebaseNaturalLanguage.getInstance().getTranslator(options)
        firebaseTranslator.downloadModelIfNeeded()
            .addOnSuccessListener {
                firebaseTranslator.translate(source)
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