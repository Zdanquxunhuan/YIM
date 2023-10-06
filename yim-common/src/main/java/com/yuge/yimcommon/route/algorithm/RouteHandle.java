package com.yuge.yimcommon.route.algorithm;

import java.util.List;

/**
 * Routing through a group of servers (select a node from a given list of servers and return its address)
 */
public interface RouteHandle {

    String routeServer(List<String> serversAddr, String userId);
}
