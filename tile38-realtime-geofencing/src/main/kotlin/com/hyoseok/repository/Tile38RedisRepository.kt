package com.hyoseok.repository

import io.lettuce.core.api.sync.RedisCommands
import io.lettuce.core.codec.StringCodec
import io.lettuce.core.output.KeyValueOutput
import io.lettuce.core.output.ListOfMapsOutput
import io.lettuce.core.output.MapOutput
import io.lettuce.core.output.StatusOutput
import io.lettuce.core.output.StringListOutput
import io.lettuce.core.output.ValueListOutput
import io.lettuce.core.output.ValueOutput
import io.lettuce.core.protocol.CommandArgs
import io.lettuce.core.protocol.CommandType
import mu.KotlinLogging
import org.springframework.stereotype.Repository

@Repository
class Tile38RedisRepository(
    private val tile38RedisCommands: RedisCommands<String, String>,
) {

    private val logger = KotlinLogging.logger {}
    private val stringCodec = StringCodec.UTF8

    fun set(vararg args: String) {
        val setResult: String = tile38RedisCommands.dispatch(
            CommandType.SET,
            StatusOutput(stringCodec),
            CommandArgs(stringCodec).apply { args.forEach { add(it) } },
        )
        logger.info { "set result: $setResult" }
    }

    fun get(vararg args: String): String {
        return tile38RedisCommands.dispatch(
            CommandType.GET,
            ValueOutput(stringCodec),
            CommandArgs(stringCodec).apply { args.forEach { add(it) } },
        )
    }
}
