package com.damai.wine.service.cache.impl;

import com.damai.wine.service.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;


/**
 * @author yueyp
 */
@Service("cacheService")
public class CacheServiceImpl implements CacheService {

    @Autowired
    LocalCacheServiceImpl localCacheServiceImpl;

    @Override
    public boolean deleteCacheByKey(String key) {
        localCacheServiceImpl.deleteCache(key);
        return true;
    }

    @Override
    public <T> T getFromCacheByKey(String key, Class<T> clazz) {
        return (T) localCacheServiceImpl.getValue(key);
    }

    @Override
    public boolean putToCache(String key, Serializable obj, Long expDateSec) {
        return localCacheServiceImpl.putValue(key, obj, expDateSec.intValue());
    }

    @Override
    public boolean putToCache(String key, Serializable obj) {
        return localCacheServiceImpl.putValue(key, obj);
    }

}
