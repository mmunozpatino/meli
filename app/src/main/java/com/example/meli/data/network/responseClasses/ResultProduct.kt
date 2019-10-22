package com.example.meli.data.network.responseClasses


import com.google.gson.annotations.SerializedName

data class ResultProduct(
    @SerializedName("available_filters")
    val availableFilters: List<AvailableFilter>,
    @SerializedName("available_sorts")
    val availableSorts: List<AvailableSort>,
    val filters: List<Filter>,
    val paging: Paging,
    val query: String,
    @SerializedName("related_results")
    val relatedResults: List<Any>,
    val results: List<Result>,
    @SerializedName("secondary_results")
    val secondaryResults: List<Any>,
    @SerializedName("site_id")
    val siteId: String,
    val sort: Sort
)