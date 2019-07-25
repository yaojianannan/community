package com.example.demo.model;

import lombok.Data;

/**
 * Created by BJB139 on 2019/7/23.
 */
@Data
public class Question {
 private Integer id;
 private String title;
 private String description;
 private String tag;
 private Long gmt_create;
 private Long gmt_modified;
 private Integer creator;
 private Integer comment_count;
 private Integer view_count;
 private Integer like_count;


}
