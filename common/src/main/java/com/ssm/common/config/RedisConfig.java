package com.ssm.common.config;

import com.ssm.common.serializer.MyStringRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig {
    
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
                  throws UnknownHostException {
      RedisTemplate<String, Object> template = new RedisTemplate();
      template.setConnectionFactory(redisConnectionFactory);
      StringRedisSerializer stringRedisSerializer=new StringRedisSerializer();
      MyStringRedisSerializer myStringRedisSerializer=new MyStringRedisSerializer();
      template.setKeySerializer(stringRedisSerializer);
      template.setValueSerializer(myStringRedisSerializer);
      template.setHashKeySerializer(stringRedisSerializer);
      template.setHashValueSerializer(myStringRedisSerializer);
      return template;
    }

}
