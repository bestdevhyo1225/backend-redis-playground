package com.hyoseok.controller

import com.hyoseok.service.RankingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RankingController(
    private val rankingService: RankingService,
) {

    @GetMapping("/setUserScore") // 등록 및 수정 기능이지만, 간단하게 '@GetMapping' 을 사용하겠다.
    fun setUserScore(@RequestParam userId: String, @RequestParam score: Double): Boolean {
        return rankingService.setUserScore(userId = userId, score = score)
    }

    @GetMapping("/getUserRank")
    fun getUserRank(@RequestParam userId: String): Long {
        return rankingService.getUserRank(userId = userId)
    }

    @GetMapping("/getTopRanks")
    fun getTopRanks(@RequestParam(defaultValue = "5") limit: Long): List<String> {
        return rankingService.getTopRanks(limit = limit)
    }
}
