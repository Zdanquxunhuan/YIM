package com.yuge.yimcommon.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class YIMMessage {

    private Long requestId;
    private Integer Type;
    private String data;
}
