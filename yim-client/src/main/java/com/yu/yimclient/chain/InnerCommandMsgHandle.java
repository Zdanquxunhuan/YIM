package com.yu.yimclient.chain;

import com.yu.yimclient.adapter.AllCommandAdapters;
import com.yu.yimclient.adapter.CommandAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InnerCommandMsgHandle extends MsgHandle {

    @Autowired
    private AllCommandAdapters allAdapters;

    @Override
    public boolean process(String msg) {

        if(msg.startsWith(":")){

            List<CommandAdapter> allCommandAdapters = allAdapters.getAllCommandAdapters();
            for (CommandAdapter adapter : allCommandAdapters) {
                if(adapter.support(msg))
                    adapter.process(msg);
            }

            return true;
        }
        return false;
    }
}
