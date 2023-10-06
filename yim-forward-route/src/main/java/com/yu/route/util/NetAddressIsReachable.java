package com.yu.route.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class NetAddressIsReachable {

    /**
     * Check whether a network address
     * (specified by its IP address or host name and port number) is accessible for a certain timeout duration
     * @param ip
     * @param host
     * @param timeout
     * @return True if connection successful
     */
    public static boolean checkAddressReachable(String ip,int host,int timeout){
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(ip,host),timeout);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}
