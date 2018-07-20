package com.narumasolutions.just4roomies.Model.Response

import android.databinding.BaseObservable

data class GetProfilesResponse(val Pagination: Pagination, val Roomies: List<Roomie>) : BaseObservable()