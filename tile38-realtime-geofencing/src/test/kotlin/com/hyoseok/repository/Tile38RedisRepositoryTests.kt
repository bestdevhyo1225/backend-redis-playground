package com.hyoseok.repository

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class Tile38RedisRepositoryTests {

    @Autowired
    private lateinit var tile38RedisRepository: Tile38RedisRepository

    @Test
    fun `Tile38 실시간 지오펜싱 서버에 위치정보를 저장한다`() {
        // given
        val key = Tile38Keys.FLEET
        val id1 = "truck1"
        val id2 = "truck2"
        val id3 = "truck3"

        // when
        tile38RedisRepository.set(
            key,
            id1,
            *Tile38Queries.pointQuery(coordinate = Tile38Coordinate(latitude = 33.5123, longitude = -112.2693))
        )
        tile38RedisRepository.set(
            key,
            id2,
            *Tile38Queries.pointQuery(coordinate = Tile38Coordinate(latitude = 100.0002, longitude = -100.0000))
        )
        tile38RedisRepository.set(
            key,
            id3,
            *Tile38Queries.pointQuery(coordinate = Tile38Coordinate(latitude = 100.0003, longitude = -100.0000))
        )

        // then
        val getResult = tile38RedisRepository.get(key, id1)

        println("getResult: $getResult")

        val limitedByRadius = "5000" // 5000m
        val nearbyResult: Map<String, Any> = tile38RedisRepository.nearby(
            key,
            *Tile38Queries.limitQuery(limit = 3),
            *Tile38Queries.pointQuery(coordinate = Tile38Coordinate(latitude = 100.0000, longitude = -100.0000)),
            limitedByRadius,
        )

        println("nearbyResult: $nearbyResult")

        val items: List<*> = nearbyResult["items"] as List<*>
        items.forEach {
            val subItems: List<*> = it as List<*>

            println("subItems[0]: ${subItems[0]}")
            println("subItems[1]: ${subItems[1]}")

            val subItemsMap: Map<*, *> = jacksonObjectMapper().readValue(subItems[1].toString(), Map::class.java)
            val coordinates: List<*> = subItemsMap["coordinates"] as List<*>
            val tile38NearbyData = Tile38NearbyData(
                id = subItems[0].toString(),
                value = Tile38NearbyValue(
                    type = subItemsMap["type"].toString(),
                    coordinate = Tile38Coordinate(
                        latitude = coordinates[1].toString().toDouble(),
                        longitude = coordinates[0].toString().toDouble(),
                    )
                )
            )

            println("tile38NearbyData = $tile38NearbyData")
        }
    }
}
