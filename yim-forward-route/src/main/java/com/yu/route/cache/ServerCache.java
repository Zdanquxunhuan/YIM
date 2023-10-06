package com.yu.route.cache;

import com.google.common.cache.LoadingCache;
import com.yu.route.kit.ZKKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
@Component
public class ServerCache {

    @Autowired
    private LoadingCache<String,String> cache;

    @Autowired
    private ZKKit zkKit;

    public void updateCache(List<String> currentChilds) {
        //Update all caches: Delete, then add
        cache.invalidateAll();
        // currentChildren=ip-127.0.0.1:11212:9082 or 127.0.0.1:11212:9082
        for (String currentChild : currentChilds) {
            String key ;
            if (currentChild.split("-").length == 2){
                key = currentChild.split("-")[1];
            }else {
                key = currentChild ;
            }
            addCache(key);
        }
    }

    private void addCache(String key) {
        cache.put(key,key);
    }

    public List<String> getAllServerList(){

        List<String> servers = new ArrayList<>();
        if(cache.size() == 0){
            //ip-127.0.0.1:11211:8081
            List<String> allNodes = zkKit.getAllNodes();
            for (String allNode : allNodes) {
                String key = allNode.split("-")[1];
                addCache(key);
            }
        }

        for (Map.Entry<String, String> entry : cache.asMap().entrySet()) {
            servers.add(entry.getKey());
        }
        return servers;
    }

    public void rebuildServersCache(){
        updateCache(getAllServerList());
    }
}
