package com.example.demo.dto;

import com.example.demo.model.User;
import lombok.Data;

/**
 * Created by BJB139 on 2019/7/24.
 */
@Data
public class QuestionDTO
{
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
    private User user;

}
