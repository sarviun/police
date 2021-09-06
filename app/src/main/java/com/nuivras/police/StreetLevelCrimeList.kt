package com.nuivras.police


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class StreetLevelCrime(
    @Json(name = "category")
    val category: String?,
    @Json(name = "location_type")
    val locationType: String?,
    @Json(name = "location")
    val location: Location?,
    @Json(name = "context")
    val context: String?,
    @Json(name = "outcome_status")
    val outcomeStatus: OutcomeStatus?,
    @Json(name = "persistent_id")
    val persistentId: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "location_subtype")
    val locationSubtype: String?,
    @Json(name = "month")
    val month: String?
) {
    @JsonClass(generateAdapter = true)
    data class Location(
        @Json(name = "latitude")
        val latitude: String?,
        @Json(name = "street")
        val street: Street?,
        @Json(name = "longitude")
        val longitude: String?
    ) {
        @JsonClass(generateAdapter = true)
        data class Street(
            @Json(name = "id")
            val id: Int?,
            @Json(name = "name")
            val name: String?
        )
    }

    @JsonClass(generateAdapter = true)
    data class OutcomeStatus(
        @Json(name = "category")
        val category: String?,
        @Json(name = "date")
        val date: String?
    )
}
