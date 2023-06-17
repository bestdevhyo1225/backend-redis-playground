# Pub/Sub 기반의 Chat Application

## Redis Topic

- `Publish / Subscribe` 구조에서 사용되는 `Queue` 를 일반적으로 `Topic` 이라고 한다.

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
