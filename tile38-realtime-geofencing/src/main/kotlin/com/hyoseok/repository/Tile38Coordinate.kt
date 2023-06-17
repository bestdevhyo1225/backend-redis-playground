package com.hyoseok.repository

data class Tile38Coordinate(
    val latitude: Double,
    val longitude: Double,
) {
    val x: Double
        get() = longitude

    val y: Double
        get() = latitude
}
