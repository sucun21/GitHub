package vn.sogo.lmscms.services.implservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import vn.sogo.lmscms.settings.RedisKeyEntity;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Created by VinhLe on 4/17/2017.
 */
public abstract class BaseService {

    /*----------------------------------- Variable $BaseService ---------------------------------------------*/

    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    
    protected static final String redisKeyPatternStudentTimline = "FE_STUDENT_TIMELINE_TRAINEE_%s_COURSE_%s_*";

    /*----------------------------------- Method $BaseService ---------------------------------------------*/


    protected Object getCache(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    protected void writeCache(String key, Object obj) {
    	writeCache(key, obj, RedisKeyEntity.DEFAULT_SECONDS_CACHE);
    }

    protected void writeCache(String key, Object obj, int seconds) {
        if ((obj instanceof Serializable || obj instanceof Iterable) && obj != null)
            redisTemplate.opsForValue().set(key, obj, seconds, TimeUnit.SECONDS);
    }

    protected void deleteCache(String pattern) {
        redisTemplate.delete(redisTemplate.keys(pattern));
    }

}
