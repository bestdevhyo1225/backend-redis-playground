package com.hyoseok.service

import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.stereotype.Service
import java.util.Scanner

@Service
class ChatService(
    private val redisMessageListenerContainer: RedisMessageListenerContainer,
    private val redisTempate: RedisTemplate<String, String>,
) : MessageListener {

    fun enterChatRoom(chatRoomName: String) {
        redisMessageListenerContainer.addMessageListener(this, ChannelTopic(chatRoomName))

        val scanner = Scanner(System.`in`)
        while (scanner.hasNextLine()) {
            val line: String = scanner.nextLine()
            if (line == "q") {
                println("Quit...")
                break
            }
            // 사용자가 메시지 전송
            redisTempate.convertAndSend(chatRoomName, line)
        }

        redisMessageListenerContainer.removeMessageListener(this)
    }

    override fun onMessage(message: Message, pattern: ByteArray?) {
        println("Message: $message")
    }
}
