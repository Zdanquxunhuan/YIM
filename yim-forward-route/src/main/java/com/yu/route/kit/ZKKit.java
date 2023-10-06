package com.yu.route.kit;

import com.alibaba.fastjson.JSON;
import com.yu.route.cache.ServerCache;
import com.yu.route.config.AppConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ZKKit {

    @Autowired
    private ZkClient zkClient;

    @Autowired
    private AppConfiguration appConfiguration;

    @Autowired
    private ServerCache serverCache;


    public void subscribeEvent(String path){
        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            //for listening on zk child changes for a given pat
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                log.info("Clear and update local cache parentPath=[{}],currentChildren=[{}]", parentPath,currentChilds.toString());

                //update local cache, delete and save.
                serverCache.updateCache(currentChilds);
            }
        });
    }

    /**
     * get all server node from zookeeper
     */
    public List<String> getAllNodes(){
        List<String> children = zkClient.getChildren(appConfiguration.getZkRoot());
        log.info("query all nodes [{}] successfully", JSON.toJSONString(children));
        return children;
    }
}
