package com.yu.yimclient.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class YIMServerResDTO implements Serializable {
    private Integer code;
    private String message;
    private ServerInfo serverInfo;

    @Data
    public static class ServerInfo{
        private String ip;
        private Integer host;
    }
}
