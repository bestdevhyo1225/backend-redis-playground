package com.hyoseok

import com.hyoseok.service.ChatService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PubSubChatApplication(
    private val chatService: ChatService,
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        println("Application started...")

        chatService.enterChatRoom(chatRoomName = "chatRoom-1")
    }
}

fun main(args: Array<String>) {
    runApplication<PubSubChatApplication>(*args)
}
