package com.hyoseok.repository

object Tile38Queries {
    fun pointQuery(coordinate: Tile38Coordinate) =
        arrayOf("POINT", coordinate.latitude.toString(), coordinate.longitude.toString())
}
