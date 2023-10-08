package com.yu.route.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public final class RouteInfo {
    private String ip ;
    private Integer yimServerPort;
    private Integer httpPort;
}
