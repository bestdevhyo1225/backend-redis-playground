package com.hyoseok.repository

import io.lettuce.core.api.sync.RedisCommands
import io.lettuce.core.codec.StringCodec
import io.lettuce.core.output.NestedMultiOutput
import io.lettuce.core.output.StatusOutput
import io.lettuce.core.output.ValueOutput
import io.lettuce.core.protocol.CommandArgs
import mu.KotlinLogging
import org.springframework.stereotype.Repository

@Repository
class Tile38RedisRepository(
    private val tile38RedisCommands: RedisCommands<String, String>,
) {

    private val logger = KotlinLogging.logger {}
    private val stringCodec = StringCodec.UTF8

    fun set(vararg args: Any) {
        val setResult: String = tile38RedisCommands.dispatch(
            Tile38Commands.SET.protocolKeyword,
            StatusOutput(stringCodec),
            CommandArgs(stringCodec).apply { args.forEach { add(it.toString()) } },
        )
        logger.info { "set result: $setResult" }
    }

    fun get(vararg args: String): String {
        return tile38RedisCommands.dispatch(
            Tile38Commands.GET.protocolKeyword,
            ValueOutput(stringCodec),
            CommandArgs(stringCodec).apply { args.forEach { add(it) } },
        )
    }

    fun nearby(vararg args: Any): Map<String, Any> {
        val nearbyResult: List<Any> = tile38RedisCommands.dispatch(
            Tile38Commands.NEARBY.protocolKeyword,
            NestedMultiOutput(stringCodec),
            CommandArgs(stringCodec).apply { args.forEach { add(it.toString()) } },
        )
        return mapOf(
            "cursor" to nearbyResult[0].toString().toInt(),
            "items" to nearbyResult[1] as List<*>,
        )
    }
}
