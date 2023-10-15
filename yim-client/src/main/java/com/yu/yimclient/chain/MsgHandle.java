package com.yu.yimclient.chain;

/**
 * 信息处理责任链
 */
public abstract class MsgHandle {

    MsgHandle nextMsgHandle;

    private void setNextMsgHandle(MsgHandle nextOne){
        this.nextMsgHandle = nextOne;
    }

    public abstract boolean process(String msg);

    public void msgChainDoHandle(String msg){
        if(process(msg))
            nextMsgHandle.process(msg);
    }

}
