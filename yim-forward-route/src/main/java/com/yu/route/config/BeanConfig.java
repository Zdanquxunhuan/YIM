package com.yu.route.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.yuge.yimcommon.route.algorithm.RouteHandle;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BeanConfig {

    @Autowired
    private AppConfiguration appConfiguration;

    @Bean
    public ZkClient buildZkClient(){
        return new ZkClient(appConfiguration.getZkAddr(),appConfiguration.getZkConnectTimeout());
    }

    @Bean
    public LoadingCache<String,String> buildLocalCache(){
        return CacheBuilder.newBuilder()
                .build(new CacheLoader<String, String>() {
                    /**
                     * This means that if a key is requested from the cache and it's not found,
                     *  the load method will be called, but it will always return null.
                     */
                    @Override
                    public String load(String key) throws Exception {
                        return null;
                    }
                });
    }

    @Bean
    public RouteHandle buildRouteHandle() throws Exception {

        RouteHandle routeHandle = (RouteHandle)Class.forName(appConfiguration.getRouteMethod()).newInstance();
        log.info("Current route algorithm is [{}]",routeHandle.getClass().getSimpleName());
        return routeHandle;

    }
}
