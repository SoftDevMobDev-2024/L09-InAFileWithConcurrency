package au.edu.swin.sdmd.l08_inafile.model

import kotlinx.serialization.Serializable

@Serializable
data class NumberFact(
    val text: String,
    val found: Boolean,
    val number: Int,
    val type: String
)