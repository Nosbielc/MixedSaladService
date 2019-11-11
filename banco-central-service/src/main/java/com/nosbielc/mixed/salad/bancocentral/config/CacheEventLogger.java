package com.nosbielc.mixed.salad.bancocentral.config;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 08/11/2019
 * @project roteirizador
 */
public class CacheEventLogger
        implements CacheEventListener<Object, Object> {

    private static final Logger log = LoggerFactory.getLogger(CacheEventLogger.class);

    @Override
    public void onEvent(
            CacheEvent<? extends Object, ? extends Object> cacheEvent) {
//        log.info(" message ", cacheEvent.getKey(), cacheEvent.getOldValue(), cacheEvent.getNewValue());
        log.info("Cache event {} for item with key {}. Old value = {}, New value = {}",
                cacheEvent.getType(), cacheEvent.getKey(), cacheEvent.getOldValue(), cacheEvent.getNewValue());
    }
}
