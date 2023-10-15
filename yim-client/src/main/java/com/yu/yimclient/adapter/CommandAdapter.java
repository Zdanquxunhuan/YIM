package com.yu.yimclient.adapter;

public interface CommandAdapter {

    String commandType();

    String desc();

    void process(String msg);

    default boolean support(String command){

        if(commandType().equals(command))
            return true;

        return false;
    }
}
