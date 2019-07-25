package com.example.demo.model;

import lombok.Data;

/**
 * Created by BJB139 on 2019/7/21.
 */
@Data
public class User {
    private Integer id;
    private String account_id;
    private String name;
    private String token;
    private Long gmt_create;
    private Long gmt_modified;
    private String avatar_url;


}
