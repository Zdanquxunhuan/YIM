package com.yu.yimclient.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AllCommandAdapters {

    private List<CommandAdapter> allAdapters = new ArrayList<>();

    @Autowired
    private GetAllCommand getAllCommand;

    @PostConstruct
    public void doInit(){
        allAdapters.add(getAllCommand);
    }

    public List<CommandAdapter> getAllCommandAdapters(){
        return allAdapters;
    }
}
