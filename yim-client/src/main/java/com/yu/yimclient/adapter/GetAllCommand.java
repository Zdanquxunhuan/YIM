package com.yu.yimclient.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GetAllCommand implements CommandAdapter {

    @Override
    public String commandType() {
        return ":all";
    }

    @Override
    public String desc() {
        return "Get all commands";
    }

    @Override
    public void process(String msg) {
        log.info("do process");
    }
}
