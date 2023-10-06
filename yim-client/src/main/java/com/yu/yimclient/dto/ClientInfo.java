package com.yu.yimclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientInfo {
    private String userName;
    private long userId ;
    private String serviceInfo ;
    private Date startDate ;
}
