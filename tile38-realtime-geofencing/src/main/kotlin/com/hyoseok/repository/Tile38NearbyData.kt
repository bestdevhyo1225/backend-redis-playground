package com.hyoseok.repository

data class Tile38NearbyData(
    val id: String,
    val value: Tile38NearbyValue,
)

data class Tile38NearbyValue(
    val type: String,
    val coordinate: Tile38Coordinate,
)
