package com.yu.yimclient.chain;

import com.yu.yimclient.util.SensitiveWordFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Check the content of the message
 */
@Component
public class CheckMsgHandle extends MsgHandle {

    @Autowired
    private SensitiveWordFilter sensitiveWordFilter;
    /**
     * 1. not null
     * 2. The message does not contain sensitive words
     */
    @Override
    public boolean process(String msg) {
        if(StringUtils.isEmpty(msg) || sensitiveWordFilter.hasSensitiveWord(msg))
            return true;

        return false;
    }
}
