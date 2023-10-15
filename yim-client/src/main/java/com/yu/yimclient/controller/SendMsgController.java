package com.yu.yimclient.controller;

import com.yu.yimclient.client.YIMClient;
import com.yu.yimclient.dto.GoogleProtocolDTO;
import com.yuge.yimcommon.enums.StatusEnum;
import com.yuge.yimcommon.res.BaseResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMsgController {

    @Autowired
    private YIMClient sendMsgUtils;

    @ApiOperation("Sending a message to the server : Google ProtoBuf")
    @PostMapping("/sendProtoBufMsg")
    public BaseResponse sendProtoBufMsg(@RequestBody GoogleProtocolDTO googleProtocolDTO){
        BaseResponse res = new BaseResponse<>();

        sendMsgUtils.sendGoogleProtocolMsg(googleProtocolDTO);

        res.setCode(StatusEnum.SUCCESS.code());
        res.setMessage(StatusEnum.SUCCESS.message());
        return res;
    }
}
