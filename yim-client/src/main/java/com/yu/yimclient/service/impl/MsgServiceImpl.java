package com.yu.yimclient.service.impl;

import com.yu.yimclient.service.MsgService;
import org.springframework.stereotype.Service;

@Service
public class MsgServiceImpl implements MsgService {

    @Override
    public void aiChat(String msg) {
        msg = msg.replace("吗", "");
        msg = msg.replace("嘛", "");
        msg = msg.replace("?", "!");
        msg = msg.replace("？", "!");
        msg = msg.replace("你", "我");
        System.out.println("AI:\033[31;4m" + msg + "\033[0m");
    }

    @Override
    public void normal(String msg) {

    }
}
