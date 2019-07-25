package com.example.demo.dto;

import lombok.Data;

/**
 * Created by BJB139 on 2019/7/18.
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;


}
