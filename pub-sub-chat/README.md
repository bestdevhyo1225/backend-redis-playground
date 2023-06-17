# Pub/Sub 기반의 Chat Application

## Redis Topic

- `Publish / Subscribe` 구조에서 사용되는 `Queue` 를 일반적으로 `Topic` 이라고 한다.

## MessageListener

```kotlin
@Service
class ChatService: MessageListener {
    override fun onMessage(message: Message, pattern: ByteArray?) {
        println("Message: $message")
    }
}
```

- `MessageListener` 인터페이스를 구현해야 한다.
- 인터페이스 구현체인 `ChatService` 는 `RedisMessageListenerContainer` 를 통해 채널을 구독하고, 구독 해제할 수 있다.

## RedisMessageListenerContainer

```kotlin
redisMessageListenerContainer.addMessageListener(this, ChannelTopic(chatRoomName))
```

- `addMessageListener` 메소드를 통해 채널을 구독한다.

```kotlin
redisMessageListenerContainer.removeMessageListener(this)
```

- `removeMessageListener` 메소드를 통해 채널을 구독을 해제한다.

## RedisTemplate

```kotlin
redisTempate.convertAndSend(chatRoomName, line)
```

- Redis 서버로 메시지를 전송하며, 해당 채널을 구독하고 있는 모든 사용자에게 메시지가 발행된다.

## 간단 정리한 내용

- [Redis Pub/Sub](https://github.com/bestdevhyo1225/dev-log/blob/master/Redis/Pub_Sub.md)
