package com.hyoseok.service

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service

@Service
class RankingService(
    private val stringRedisTemplate: StringRedisTemplate,
) {

    private val LEADER_BOARD_KEY: String = "leader:board"

    fun setUserScore(userId: String, score: Double): Boolean {
        return stringRedisTemplate
            .opsForZSet()
            .add(LEADER_BOARD_KEY, userId, score) ?: throw RuntimeException("'zadd' 결과가 null 입니다.")
    }

    fun getUserRank(userId: String): Long {
        return stringRedisTemplate
            .opsForZSet()
            .reverseRank(LEADER_BOARD_KEY, userId) ?: throw RuntimeException("'zrevrank' 결과가 null 입니다.")
    }

    fun getTopRanks(limit: Long): List<String> {
        val start: Long = 0
        val end: Long = limit - 1
        val topRanks: Set<String> = stringRedisTemplate
            .opsForZSet()
            .reverseRange(LEADER_BOARD_KEY, start, end) ?: throw RuntimeException("'zrevrange' 결과가 null 입니다.")

        return topRanks.map { it }
    }
}
