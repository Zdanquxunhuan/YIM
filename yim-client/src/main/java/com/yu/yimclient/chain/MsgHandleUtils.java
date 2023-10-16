package com.yu.yimclient.chain;

import com.yu.yimclient.util.SpringBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MsgHandleUtils {

    @Autowired
    private CheckMsgHandle checkMsgHandle;
    @Autowired
    private InnerCommandMsgHandle innerCommandMsgHandle;
    @Autowired
    private SendMsgHandle sendMsgHandle;

    public MsgHandle assembleMsgHandle(){

        checkMsgHandle.setNextMsgHandle(innerCommandMsgHandle);
        innerCommandMsgHandle.setNextMsgHandle(sendMsgHandle);

        return checkMsgHandle;
    }
}
