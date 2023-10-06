package com.yu.yimclient.dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class YIMServerResDTO implements Serializable {
    private String code;
    private String message;
    private Object reqNo;
    private ServerInfo serverInfo;

    @Data
    @ToString
    public static class ServerInfo{
        private String ip;
        private Integer host;
    }
}
