package com.baoshine.questionnaire.config.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
@RequiredArgsConstructor
public class CacheMessageListener implements MessageListener {

	private final RedisTemplate<Object, Object> redisTemplate;

	private final RedisCaffeineCacheManager redisCaffeineCacheManager;

	@Override
	public void onMessage(Message message, byte[] pattern) {
		CacheMessage cacheMessage = (CacheMessage) redisTemplate.getValueSerializer().deserialize(message.getBody());
		if (cacheMessage != null) {
			log.debug("recevice a redis topic message, clear local cache, the cacheName is {}, the key is {}",
					cacheMessage.getCacheName(), cacheMessage.getKey());
			redisCaffeineCacheManager.clearLocal(cacheMessage.getCacheName(), cacheMessage.getKey());
		}
	}
}