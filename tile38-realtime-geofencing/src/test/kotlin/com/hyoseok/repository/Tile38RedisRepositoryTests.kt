package com.hyoseok.repository

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
        val id = "truck1"
        val pointQuery =
            Tile38Queries.pointQuery(coordinate = Tile38Coordinate(latitude = 33.5123, longitude = -112.2693))

        // when
        tile38RedisRepository.set(key, id, *pointQuery)

        // then
        val value = tile38RedisRepository.get(key, id)

        println("value: $value")
    }
}
