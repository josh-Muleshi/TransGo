package cd.transgo.transgo.data.model

data class Advice(
    val query: String? = "",
    val slips: List<Slip?>?,
    val total_results: String? = ""
)