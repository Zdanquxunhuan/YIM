package com.yu.yimserver.kit;

import com.yu.yimserver.config.AppConfiguration;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ZKKit {

    @Autowired
    private AppConfiguration appConfiguration;

    @Autowired
    private ZkClient zkClient;


    /**
     * create parent node
     */
    public void createRootNode() {

        //Check whether the specified path in ZooKeeper exists
        boolean exists = zkClient.exists(appConfiguration.getZkRoot());

        if (exists)
            return;

        //Create a persistent node.
        zkClient.createPersistent(appConfiguration.getZkRoot());
    }

    /**
     *
     * Create an ephemeral node.
     *
     * Unlike persistent nodes, the lifetime of a temporary node is tied to a client session,
     * and the node is automatically deleted when the client session ends.
     * Therefore, temporary nodes are usually used to achieve some short-term tasks or status information records,
     * such as service registration, load balancing, and leader election scenarios
     * @param path
     */
    public void createNode(String path) {
        zkClient.createEphemeral(path);
    }
}
