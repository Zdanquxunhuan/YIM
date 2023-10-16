package com.yu.yimclient.chain;

import com.yu.yimclient.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SendMsgHandle extends MsgHandle {

    @Value("${chat.aiModel}")
    private boolean aiModel;

    @Autowired
    private MsgService msgService;

    @Override
    public boolean process(String msg) {

        if (aiModel)
            msgService.aiChat(msg);
        else
            msgService.normal(msg);

        return true;
    }
}
