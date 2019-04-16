package com.xz.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;


//@Configuration
//@AutoConfigureAfter(DisconfConfig.class)
public class RedisConfiguration extends CachingConfigurerSupport {
//    Logger logger = LoggerFactory.getLogger(RedisConfiguration.class);
//    @Autowired
//    private RedisConfig  redisConfig;
//
//    @Bean(name = "jedisPool")
//    public JedisPool redisPoolFactory() {
//
//        logger.info("redis开始加载");
//        String password=redisConfig.getPassword();
//        String host = redisConfig.getHost();
//        int port = redisConfig.getPort();
//        int timeout = redisConfig.getTimeout();
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxIdle(redisConfig.getMaxIdle());
//        jedisPoolConfig.setMaxWaitMillis(redisConfig.getMaxWaitMillis());
//        JedisPool jedisPool = null;
//
//        if(password == null || password.equals("")){
//        	jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
//        }else{
//        	jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout,password);
//        }
//        logger.info("redis加载完成！");
//        return jedisPool;
//    }

}
