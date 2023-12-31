package com.yu.route.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class YIMServerResDTO {
    private String ip;
    private int httpPort;
    private int yimServerPort;

    public YIMServerResDTO(RouteInfo routeInfo) {
        this.ip = routeInfo.getIp();
        this.httpPort = routeInfo.getHttpPort();
        this.yimServerPort = routeInfo.getYimServerPort();
    }
}
