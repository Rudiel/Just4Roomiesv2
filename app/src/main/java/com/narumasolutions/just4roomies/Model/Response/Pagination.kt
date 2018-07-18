package com.narumasolutions.just4roomies.Model.Response

data class Pagination(val count: Int,val limit : Int,val next: String, val previous : String, val start : Int)