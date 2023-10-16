package com.yu.yimclient.service;

public interface MsgService {

    /**
     * Talk to ai
     * @param msg
     */
    void aiChat(String msg);

    /**
     * normal conversation
     * @param msg
     */
    void normal(String msg);
}
