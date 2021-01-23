package com.damai.wine.service.cache;


import com.damai.wine.exception.WineException;

import java.io.Serializable;

/**
 * @author 作者 yueyp
 * @date 创建时间：2020年12月16日 下午8:15:17
 * @version 1.0
 */
 public  interface CacheService {

	 /**
     * 根据键值删除信息
     * @param key
     * @return
     * @throws WineException
     */
     boolean deleteCacheByKey(String key) ;
    
    /**
     * 
     * @param key
     * @param clazz
     * @return
     * @throws WineException
     */
     <T> T getFromCacheByKey(String key, Class<T> clazz);
    
    
    /**
     * 
     * @param key
     * @param obj
     * @param expDateSec
     * @return
     * @throws WineException
     */
     boolean putToCache(String key, Serializable obj, Long expDateSec);
    
    
    /**
     * 
     * @param key
     * @param obj
     * @return
     * @throws WineException
     */
     boolean putToCache(String key, Serializable obj) throws WineException;
}
