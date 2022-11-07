package cd.transgo.transgo.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Advice(
    val query: String? = "",
    val slips: List<Slip?>?,
    val total_results: String? = ""
) : Parcelable