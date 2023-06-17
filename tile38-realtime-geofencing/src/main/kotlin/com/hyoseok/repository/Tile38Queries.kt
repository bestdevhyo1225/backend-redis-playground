package com.hyoseok.repository

object Tile38Queries {
    fun pointQuery(coordinate: Tile38Coordinate) = arrayOf("POINT", coordinate.latitude, coordinate.longitude)
    fun limitQuery(limit: Int) = arrayOf("LIMIT", limit)
}
